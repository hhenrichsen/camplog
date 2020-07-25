package com.github.hhenrichsen.camplog.data.repository

import com.github.hhenrichsen.camplog.data.models.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.util.*

@DataJpaTest
class TestUserRepository @Autowired constructor(
        private val entityManager: TestEntityManager,
        private val userRepository: UserRepository) {
    @Test
    fun `Should return user by Tag`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), version = null)
        entityManager.persist(user)
        entityManager.flush()
        val foundName = userRepository.findByTag("hhenrichsen").get()
        assertThat(foundName).isEqualTo(user)
    }

    @Test
    fun `Should return user by Email`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), version = null)
        entityManager.persist(user)
        entityManager.flush()
        val foundName = userRepository.findByEmail("hunter.henrichsen@gmail.com").get()
        assertThat(foundName).isEqualTo(user)
    }

    @Test
    fun `Should return user by ID`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), version = null)
        entityManager.persist(user)
        entityManager.flush()
        val id = user.id
        val foundId = userRepository.findById(id).get()
        assertThat(foundId).isEqualTo(user)
    }

    @Test
    fun `Should return only public users`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), public = true, version = null)
        val user2 = User("henrick.hunterson@gmail.com", "hhunterson", "Henrik Hunterson", "", Date(), mutableListOf(), version = null)

        entityManager.persist(user)
        entityManager.persist(user2)
        entityManager.flush()

        val found = userRepository.findByPublicTrue().iterator()
        val found1 = found.next()
        assertThat(found1).isEqualTo(user)
        assertThat(found.hasNext()).isEqualTo(false)
    }
}