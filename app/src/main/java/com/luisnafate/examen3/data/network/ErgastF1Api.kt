package com.luisnafate.examen3.data.network

import com.luisnafate.examen3.data.model.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ErgastF1Api {
    
    @GET("current.json")
    suspend fun getCurrentSeasonRaces(): RaceResponse
    
    @GET("{season}.json")
    suspend fun getRacesByYear(
        @Path("season") season: Int,
        @Query("limit") limit: Int = 30
    ): RaceResponse
    
    @GET("{season}/{round}/results.json")
    suspend fun getRaceResults(
        @Path("season") season: Int,
        @Path("round") round: Int
    ): RaceResultResponse
    
    @GET("{season}/{round}/qualifying.json")
    suspend fun getQualifyingResults(
        @Path("season") season: Int,
        @Path("round") round: Int
    ): RaceResultResponse
    
    @GET("current/drivers.json")
    suspend fun getCurrentDrivers(
        @Query("limit") limit: Int = 100
    ): DriverResponse
    
    @GET("{season}/drivers.json")
    suspend fun getDriversByYear(
        @Path("season") season: Int,
        @Query("limit") limit: Int = 100
    ): DriverResponse
    
    @GET("{season}/constructors/{constructor}/drivers.json")
    suspend fun getDriversByConstructor(
        @Path("season") season: Int,
        @Path("constructor") constructor: String
    ): DriverResponse
    
    @GET("{season}/circuits.json")
    suspend fun getCircuitsBySeason(
        @Path("season") season: Int
    ): CircuitResponse
    
    @GET("circuits.json")
    suspend fun getAllCircuits(
        @Query("limit") limit: Int = 100
    ): CircuitResponse
    
    @GET("current/driverStandings.json")
    suspend fun getCurrentDriverStandings(): StandingsResponse
    
    @GET("{season}/driverStandings.json")
    suspend fun getDriverStandings(
        @Path("season") season: Int
    ): StandingsResponse
    
    @GET("{season}/{round}/driverStandings.json")
    suspend fun getDriverStandingsAtRound(
        @Path("season") season: Int,
        @Path("round") round: Int
    ): StandingsResponse
    
    @GET("{season}/constructorStandings.json")
    suspend fun getConstructorStandings(
        @Path("season") season: Int
    ): StandingsResponse
    
    @GET("{season}/constructors.json")
    suspend fun getConstructorsBySeason(
        @Path("season") season: Int
    ): ConstructorResponse
    
    @GET("{season}/{round}/laps.json")
    suspend fun getLapTimes(
        @Path("season") season: Int,
        @Path("round") round: Int,
        @Query("limit") limit: Int = 1000
    ): LapTimesResponse
    
    @GET("{season}/{round}/laps/{lap}.json")
    suspend fun getSpecificLapTimes(
        @Path("season") season: Int,
        @Path("round") round: Int,
        @Path("lap") lap: Int
    ): LapTimesResponse
    
    @GET("{season}/{round}/pitstops.json")
    suspend fun getPitstops(
        @Path("season") season: Int,
        @Path("round") round: Int,
        @Query("limit") limit: Int = 100
    ): PitstopResponse
    
    @GET("{season}/sprint.json")
    suspend fun getSprintResults(
        @Path("season") season: Int
    ): SprintResponse
    
    @GET("{season}/{round}/sprint.json")
    suspend fun getSprintByRound(
        @Path("season") season: Int,
        @Path("round") round: Int
    ): SprintResponse
    
    @GET("seasons.json")
    suspend fun getSeasons(
        @Query("limit") limit: Int = 100
    ): SeasonResponse
    
    @GET("status.json")
    suspend fun getStatus(): StatusResponse
}
