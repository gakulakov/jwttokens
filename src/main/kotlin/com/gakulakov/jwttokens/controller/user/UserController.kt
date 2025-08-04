package com.gakulakov.jwttokens.controller.user

import com.gakulakov.jwttokens.model.Role
import com.gakulakov.jwttokens.model.User
import com.gakulakov.jwttokens.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
        userService.createUser(userRequest.toModel())?.toResponse()
            ?: throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cannot create a user"
            )

    @GetMapping
    fun listAll(): List<UserResponse> {
        return userService.findAll().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): UserResponse =
        userService.findById(id)?.toResponse()
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cannot found a user"
            )

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Boolean> {
        val isSuccess = userService.deleteById(id)

        return if (isSuccess)
            ResponseEntity.noContent().build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found a user")

    }

    private fun UserRequest.toModel(): User {
        return User(
            id = UUID.randomUUID(),
            email = this.email,
            passwod = this.password,
            role = Role.USER
        )
    }

    private fun User.toResponse(): UserResponse {
        return UserResponse(
            email = this.email,
            id = this.id
        )
    }
}