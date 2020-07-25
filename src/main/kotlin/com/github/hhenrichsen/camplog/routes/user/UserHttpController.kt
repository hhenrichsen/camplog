package com.github.hhenrichsen.camplog.routes.user

import com.github.hhenrichsen.camplog.data.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserHttpController(private val repo: UserRepository) {
    @GetMapping("/")
    fun findAll() = repo.findByPublicTrue()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String) : Any {
        val uuid = UUID.fromString(id)
        val user = repo.findById(uuid).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "A user with that ID does not exist.") }
        if (user.public) {
            return user
        }
        return 0
    }
}