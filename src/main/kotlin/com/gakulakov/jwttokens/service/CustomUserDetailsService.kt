package com.gakulakov.jwttokens.service

import com.gakulakov.jwttokens.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.gakulakov.jwttokens.model.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails = // Just find user by username from repository
        userRepository
            .findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")


    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.passwod)
            .roles(this.role.name)
            .build()
}