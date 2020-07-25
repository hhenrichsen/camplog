package com.github.hhenrichsen.camplog.routes.user

import com.github.hhenrichsen.camplog.data.models.User
import com.github.hhenrichsen.camplog.data.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/auth")
class UserController(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/register")
    fun register(@RequestBody newUser: NewUser) {
        val passwordHash = bCryptPasswordEncoder.encode(newUser.rawPassword)
        val user = User(newUser.email, newUser.tag, newUser.name, passwordHash, Date(), mutableListOf(), version = null)
        userRepository.save(user)
    }
}