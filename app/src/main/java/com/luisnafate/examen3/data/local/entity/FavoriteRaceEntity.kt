package com.luisnafate.examen3.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_races")
data class FavoriteRaceEntity(
    @PrimaryKey
    val uniqueId: String, // season-round
    val season: String,
    val round: String,
    val raceName: String,
    val circuitId: String,
    val circuitName: String,
    val locality: String,
    val country: String,
    val date: String,
    val time: String?,
    val url: String,
    val addedAt: Long = System.currentTimeMillis()
)
