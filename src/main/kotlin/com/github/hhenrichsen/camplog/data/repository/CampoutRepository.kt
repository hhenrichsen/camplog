package com.github.hhenrichsen.camplog.data.repository

import com.github.hhenrichsen.camplog.data.models.Campout
import com.github.hhenrichsen.camplog.data.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CampoutRepository : CrudRepository<Campout, UUID> {
    fun findAllByUserOrderByStartDateDesc(user: User): Iterable<Campout>
}