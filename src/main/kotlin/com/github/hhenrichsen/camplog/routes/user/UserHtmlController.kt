package com.github.hhenrichsen.camplog.routes.user

import com.github.hhenrichsen.camplog.data.models.User
import com.github.hhenrichsen.camplog.data.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/user")
class UserHtmlController(private val repo: UserRepository) {

    @GetMapping("/{tag}")
    fun user(@PathVariable tag: String, model: Model): String {
        val user = repo.findByTag(tag).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        return renderUser(user, model)
    }

    fun renderUser(user: User, model: Model): String {
        val renderedUser = user.render()
        if (!user.public) {
            model["user"] = renderedUser
            return "privateUser"
        }
        model["title"] = "CampLog | ${user.name}'s Campouts"
        model["user"] = user
        model["name"] = user.name
        model["campouts"] = user.campouts.map { it.render() }
        return "user"
    }
}