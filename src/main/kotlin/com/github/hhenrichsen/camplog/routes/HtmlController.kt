package com.github.hhenrichsen.camplog.routes

import com.github.hhenrichsen.camplog.data.models.User
import com.github.hhenrichsen.camplog.data.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(private val repository: UserRepository) {
    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "Hello"
        return "blog"
    }
}