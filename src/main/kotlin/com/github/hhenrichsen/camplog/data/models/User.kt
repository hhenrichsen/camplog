package com.github.hhenrichsen.camplog.data.models

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class User(
        var email: String,
        var tag: String,
        var name: String,
        var passwordHash: String,
        var tosDate: Date,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
        var campouts: MutableList<Campout>,
        var public: Boolean = false,
        givenId: UUID = UUID.randomUUID(),
        version: Long?) : AbstractBaseEntity(givenId, version) {

    fun addCampout(campout: Campout) {
        this.campouts.add(campout)
    }

    fun render(): RenderedUser {
        return RenderedUser(this.name, this.tag, this.campouts)
    }

    data class RenderedUser(
            val name: String,
            val tag: String,
            val campouts: List<Campout>
    )
}
