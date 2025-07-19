package com.gakulakov.jwttokens.repository

import com.gakulakov.jwttokens.model.Role
import com.gakulakov.jwttokens.model.User
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepository {
    private val users = mutableListOf(
        User(
            id = UUID.randomUUID(),
            email = "email-1@gmail.com",
            passwod = "pass1",
            role = Role.User,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-2@gmail.com",
            passwod = "pass2",
            role = Role.Admin,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-3@gmail.com",
            passwod = "pass3",
            role = Role.User,
        ),
    )

    fun save(user: User): Boolean = users.add(user)

    fun findByEmail(email: String): User? = users.firstOrNull { it.email == email }

    fun finaAll(): List<User> = users

    fun findById(id: UUID): User? = users.firstOrNull { it.id == id }

    fun deleteById(id: UUID): Boolean = users.removeIf { it.id == id }
}