package com.gakulakov.jwttokens.model

import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val passwod: String,
    val role: Role
)

enum class Role {
    User,
    Admin
}

