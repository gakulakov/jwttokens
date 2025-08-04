package com.gakulakov.jwttokens.service

import com.gakulakov.jwttokens.config.JwtProperties
import com.gakulakov.jwttokens.controller.auth.AuthenticationRequest
import com.gakulakov.jwttokens.controller.auth.AuthenticationResponse
import com.gakulakov.jwttokens.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {

        authManager.authenticate( // Just auth user by email and password
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        ) // If not found user, method throw exception with 403

        val user = userDetailsService.loadUserByUsername(authRequest.email) // Find user from repository

        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)

        return AuthenticationResponse( // Finally return access token from method
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)

        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)

            if (!tokenService.isExpired(token) && currentUserDetails.username == refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDetails)
            else
                null
        }
    }


    // Generate refresh token
    private fun generateRefreshToken(user: UserDetails): String =
        tokenService.generate(user, Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration))

    // Generate access token
    private fun generateAccessToken(user: UserDetails): String = tokenService.generate(
        userDetails = user, // User from database
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration) // Setting expiration date. .accessTokenExpiration getting value from application.yaml "access-token-expiration" variable
    )


}
