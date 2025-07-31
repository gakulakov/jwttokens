package com.gakulakov.jwttokens.repository

import com.gakulakov.jwttokens.model.Role
import com.gakulakov.jwttokens.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepository(
    private val encoder: PasswordEncoder
) {
    private val users = mutableListOf(
        User(
            id = UUID.randomUUID(),
            email = "email-1@gmail.com",
            passwod = encoder.encode("pass1"),
            role = Role.User,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-2@gmail.com",
            passwod = encoder.encode("pass2"),
            role = Role.Admin,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-3@gmail.com",
            passwod = encoder.encode("pass3"),
            role = Role.User,
        ),
    )

    fun save(user: User): Boolean {
        val updated = user.copy(passwod = encoder.encode(user.passwod))
        
        return users.add(updated)
    }

    fun findByEmail(email: String): User? = users.firstOrNull { it.email == email }

    fun finaAll(): List<User> = users

    fun findById(id: UUID): User? = users.firstOrNull { it.id == id }

    fun deleteById(id: UUID): Boolean = users.removeIf { it.id == id }
}