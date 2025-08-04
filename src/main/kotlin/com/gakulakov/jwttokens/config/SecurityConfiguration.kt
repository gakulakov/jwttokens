package com.gakulakov.jwttokens.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtAuthenticationFilter: JwtAuthenticationFilter): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth", "/api/auth/refresh", "/error") // Setting protected endpoints
                    .permitAll() // Setting roles access endpoints. Mean "permit for all roles"
                    .requestMatchers(HttpMethod.POST, "api/users") // Setting protected REST-method and endpoints (for registry in this project)
                    .permitAll()
                    .requestMatchers("api/users**") // Setting protected endpoint for role ADMIN
                    .hasRole("ADMIN") // Setting role
                    .anyRequest() // Next any requests for any endpoints need fully authenticated
                    .fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Created session without state
            }
            .authenticationProvider(authenticationProvider) // Provide auth provider
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // Use own filter
            .build()

}