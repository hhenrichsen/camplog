package com.github.hhenrichsen.camplog.data.models

import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class AbstractBaseEntity(
        @Id @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),
        @Version val version: Long? = null) {

    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other == null -> false
            other !is AbstractBaseEntity -> false
            else -> id == other.id
        }
    }
}
