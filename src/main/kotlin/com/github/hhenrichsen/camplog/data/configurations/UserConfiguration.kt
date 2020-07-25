package com.github.hhenrichsen.camplog.data.configurations

import com.github.hhenrichsen.camplog.data.models.Campout
import com.github.hhenrichsen.camplog.data.models.User
import com.github.hhenrichsen.camplog.data.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.*

@Configuration
class UserConfiguration {
    @Bean
    fun databaseInitializer(userRepository: UserRepository) = ApplicationRunner {
        val hunter = userRepository.save(User("hunter.henrichsen@gmail.com", "hhenrichsen",
            "Hunter Henrichsen", "", Date(), mutableListOf(), public = true,
            givenId = UUID.fromString("00000000-0000-0000-0000-000000000000"), version = null))
        println("http://localhost:8080/user/${hunter.id}")
        println(hunter.name)
        hunter.addCampout(Campout(
            name = "Hunter's First Campout",
            startDate = LocalDateTime.of(2012, 1, 15, 12, 0),
            endDate = LocalDateTime.of(2012, 1, 16, 12, 0),
            latitude = 0.0,
            longitude = 0.0,
            user = hunter,
            version = null
        ))
        hunter.addCampout(Campout(
            name = "Hunter's Other Campout",
            startDate = LocalDateTime.of(2013, 4, 3, 12, 0),
            endDate = LocalDateTime.of(2013, 4, 6, 15, 0),
            latitude = 0.0,
            longitude = 0.0,
            user = hunter,
            version = null
        ))
        userRepository.save(hunter)
    }
}