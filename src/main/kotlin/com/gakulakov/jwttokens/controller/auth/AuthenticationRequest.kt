package com.gakulakov.jwttokens.controller.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
)
