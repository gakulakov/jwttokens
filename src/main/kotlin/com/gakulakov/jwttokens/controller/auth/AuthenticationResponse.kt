package com.gakulakov.jwttokens.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
