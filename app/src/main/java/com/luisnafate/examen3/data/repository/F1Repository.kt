package com.luisnafate.examen3.data.repository

import com.luisnafate.examen3.data.local.F1Database
import com.luisnafate.examen3.data.mapper.toDriver
import com.luisnafate.examen3.data.mapper.toFavoriteEntity
import com.luisnafate.examen3.data.mapper.toRace
import com.luisnafate.examen3.data.model.*
import com.luisnafate.examen3.data.network.ErgastF1Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class F1Repository(
    private val api: ErgastF1Api,
    private val database: F1Database
) {
    
    suspend fun getCurrentSeasonRaces(): List<Race> {
        return api.getCurrentSeasonRaces().mrData.raceTable.races
    }
    
    suspend fun getRacesByYear(year: Int = 2024): List<Race> {
        return api.getRacesByYear(year).mrData.raceTable.races
    }
    
    suspend fun getRaceResults(year: Int, round: Int): List<RaceResult> {
        val response = api.getRaceResults(year, round)
        return response.mrData.raceTable.races.firstOrNull()?.results ?: emptyList()
    }
    
    suspend fun getQualifyingResults(year: Int, round: Int): List<RaceResult> {
        val response = api.getQualifyingResults(year, round)
        return response.mrData.raceTable.races.firstOrNull()?.results ?: emptyList()
    }
    
    suspend fun getCurrentDrivers(): List<Driver> {
        return api.getCurrentDrivers().mrData.driverTable.drivers
    }
    
    suspend fun getDriversByYear(year: Int): List<Driver> {
        return api.getDriversByYear(year).mrData.driverTable.drivers
    }
    
    suspend fun getDriversByConstructor(year: Int, constructor: String): List<Driver> {
        return api.getDriversByConstructor(year, constructor).mrData.driverTable.drivers
    }
    
    suspend fun getCircuitsBySeason(year: Int): List<Circuit> {
        return api.getCircuitsBySeason(year).mrData.circuitTable.circuits
    }
    
    suspend fun getAllCircuits(): List<Circuit> {
        return api.getAllCircuits().mrData.circuitTable.circuits
    }
    
    suspend fun getCurrentDriverStandings(): List<DriverStanding> {
        val response = api.getCurrentDriverStandings()
        return response.mrData.standingsTable.standingsLists.firstOrNull()?.driverStandings
            ?: emptyList()
    }
    
    suspend fun getDriverStandings(year: Int): List<DriverStanding> {
        val response = api.getDriverStandings(year)
        return response.mrData.standingsTable.standingsLists.firstOrNull()?.driverStandings
            ?: emptyList()
    }
    
    suspend fun getDriverStandingsAtRound(year: Int, round: Int): List<DriverStanding> {
        val response = api.getDriverStandingsAtRound(year, round)
        return response.mrData.standingsTable.standingsLists.firstOrNull()?.driverStandings
            ?: emptyList()
    }
    
    suspend fun getConstructorStandings(year: Int): List<Constructor> {
        val response = api.getConstructorStandings(year)
        return response.mrData.standingsTable.standingsLists.firstOrNull()?.constructorStandings
            ?.map { it.constructor } ?: emptyList()
    }
    
    suspend fun getConstructorsBySeason(year: Int): List<Constructor> {
        return api.getConstructorsBySeason(year).getConstructors()
    }
    
    fun getFavoriteRaces(): Flow<List<Race>> {
        return database.favoriteRaceDao().getAllFavoriteRaces()
            .map { entities -> entities.map { it.toRace() } }
    }
    
    suspend fun isRaceFavorite(uniqueId: String): Boolean {
        return database.favoriteRaceDao().isFavorite(uniqueId)
    }
    
    suspend fun addFavoriteRace(race: Race) {
        database.favoriteRaceDao().insertFavoriteRace(race.toFavoriteEntity())
    }
    
    suspend fun removeFavoriteRace(uniqueId: String) {
        database.favoriteRaceDao().deleteFavoriteRaceById(uniqueId)
    }
    
    fun getFavoriteDrivers(): Flow<List<Driver>> {
        return database.favoriteDriverDao().getAllFavoriteDrivers()
            .map { entities -> entities.map { it.toDriver() } }
    }
    
    suspend fun isDriverFavorite(driverId: String): Boolean {
        return database.favoriteDriverDao().isFavorite(driverId)
    }
    
    suspend fun addFavoriteDriver(driver: Driver) {
        database.favoriteDriverDao().insertFavoriteDriver(driver.toFavoriteEntity())
    }
    
    suspend fun removeFavoriteDriver(driverId: String) {
        database.favoriteDriverDao().deleteFavoriteDriverById(driverId)
    }
}
