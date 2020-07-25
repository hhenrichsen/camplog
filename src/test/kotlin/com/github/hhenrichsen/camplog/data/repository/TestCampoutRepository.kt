package com.github.hhenrichsen.camplog.data.repository

import com.github.hhenrichsen.camplog.data.models.Campout
import com.github.hhenrichsen.camplog.data.models.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
class TestCampoutRepository @Autowired constructor(
        val entityManager: TestEntityManager,
        val campoutRepository: CampoutRepository) {

    @Test
    fun `Should return campout by ID`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), version = null)
        val campout = Campout("My Campout", LocalDateTime.now(), LocalDateTime.now(), 0.0, 0.0, user, version = null)
        user.addCampout(campout)
        entityManager.persist(user)
        entityManager.flush()
        val found = campoutRepository.findById(campout.id).get()
        assertThat(found).isEqualTo(campout)
        assertThat(found.user.campouts.size).isEqualTo(1)
    }

    @Test
    fun `Should return campouts in order`() {
        val user = User("hunter.henrichsen@gmail.com", "hhenrichsen", "Hunter Henrichsen", "", Date(), mutableListOf(), version = null)
        val campout = Campout("My Campout", LocalDateTime.now().minusDays(1), LocalDateTime.now(), 0.0, 0.0, user, version = null)
        val campout2 = Campout("My Other Campout", LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1), 0.0, 0.0, user, version = null)
        user.addCampout(campout)
        user.addCampout(campout2)
        entityManager.persist(user)
        entityManager.flush()
        val found = campoutRepository.findAllByUserOrderByStartDateDesc(user).iterator()
        val found1 = found.next()
        val found2 = found.next()
        assertThat(found1).isEqualTo(campout)
        assertThat(found2).isEqualTo(campout2)
    }
}