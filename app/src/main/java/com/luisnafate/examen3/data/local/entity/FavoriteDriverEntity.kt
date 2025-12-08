package com.luisnafate.examen3.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_drivers")
data class FavoriteDriverEntity(
    @PrimaryKey
    val driverId: String,
    val permanentNumber: String?,
    val code: String?,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String,
    val url: String,
    val addedAt: Long = System.currentTimeMillis()
)
