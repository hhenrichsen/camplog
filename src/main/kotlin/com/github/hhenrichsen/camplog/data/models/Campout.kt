package com.github.hhenrichsen.camplog.data.models

import com.github.hhenrichsen.camplog.ext.between
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Campout (
        var name: String,
        var startDate: LocalDateTime,
        var endDate: LocalDateTime,
        var latitude: Double,
        var longitude: Double,
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        givenId: UUID = UUID.randomUUID(),
        version: Long?) : AbstractBaseEntity(givenId, version) {

    fun getDuration() : String = endDate.between(startDate).asString()

    fun render() : RenderedCampout {
        return RenderedCampout(this.name, this.startDate, this.endDate, this.getDuration())
    }

    data class RenderedCampout(
            val name: String,
            val startDate: LocalDateTime,
            val endDate: LocalDateTime,
            val duration: String)
}
