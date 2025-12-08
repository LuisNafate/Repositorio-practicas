package com.luisnafate.examen3.data.mapper

import com.luisnafate.examen3.data.local.entity.FavoriteDriverEntity
import com.luisnafate.examen3.data.local.entity.FavoriteRaceEntity
import com.luisnafate.examen3.data.model.Driver
import com.luisnafate.examen3.data.model.Race

fun Race.toFavoriteEntity(): FavoriteRaceEntity {
    return FavoriteRaceEntity(
        uniqueId = this.uniqueId,
        season = this.season,
        round = this.round,
        raceName = this.raceName,
        circuitId = this.circuit.circuitId,
        circuitName = this.circuit.circuitName,
        locality = this.circuit.location.locality,
        country = this.circuit.location.country,
        date = this.date,
        time = this.time,
        url = this.url
    )
}

fun FavoriteRaceEntity.toRace(): Race {
    return Race(
        season = this.season,
        round = this.round,
        raceName = this.raceName,
        circuit = com.luisnafate.examen3.data.model.Circuit(
            circuitId = this.circuitId,
            circuitName = this.circuitName,
            location = com.luisnafate.examen3.data.model.Location(
                lat = "0.0",
                long = "0.0",
                locality = this.locality,
                country = this.country
            )
        ),
        date = this.date,
        time = this.time,
        url = this.url
    )
}

fun Driver.toFavoriteEntity(): FavoriteDriverEntity {
    return FavoriteDriverEntity(
        driverId = this.driverId,
        permanentNumber = this.permanentNumber,
        code = this.code,
        givenName = this.givenName,
        familyName = this.familyName,
        dateOfBirth = this.dateOfBirth,
        nationality = this.nationality,
        url = this.url
    )
}

fun FavoriteDriverEntity.toDriver(): Driver {
    return Driver(
        driverId = this.driverId,
        permanentNumber = this.permanentNumber,
        code = this.code,
        givenName = this.givenName,
        familyName = this.familyName,
        dateOfBirth = this.dateOfBirth,
        nationality = this.nationality,
        url = this.url
    )
}
