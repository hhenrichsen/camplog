package com.github.hhenrichsen.camplog.data.repository

import com.github.hhenrichsen.camplog.data.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
    fun findByTag(tag: String): Optional<User>
    fun findByPublicTrue(): Iterable<User>
}