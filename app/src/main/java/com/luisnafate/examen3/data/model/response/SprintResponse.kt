package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName

data class SprintResponse(
    @SerializedName("MRData")
    val mrData: SprintMRData
)

data class SprintMRData(
    @SerializedName("RaceTable")
    val raceTable: SprintRaceTable
)

data class SprintRaceTable(
    @SerializedName("Races")
    val races: List<RaceWithSprint>
)

data class RaceWithSprint(
    @SerializedName("season")
    val season: String,
    
    @SerializedName("round")
    val round: String,
    
    @SerializedName("raceName")
    val raceName: String,
    
    @SerializedName("SprintResults")
    val sprintResults: List<SprintResult>
)

data class SprintResult(
    @SerializedName("number")
    val number: String,
    
    @SerializedName("position")
    val position: String,
    
    @SerializedName("positionText")
    val positionText: String,
    
    @SerializedName("points")
    val points: String,
    
    @SerializedName("Driver")
    val driver: SprintDriver,
    
    @SerializedName("Constructor")
    val constructor: SprintConstructor,
    
    @SerializedName("grid")
    val grid: String,
    
    @SerializedName("laps")
    val laps: String,
    
    @SerializedName("status")
    val status: String,
    
    @SerializedName("Time")
    val time: SprintTime? = null,
    
    @SerializedName("FastestLap")
    val fastestLap: SprintFastestLap? = null
)

data class SprintDriver(
    @SerializedName("driverId")
    val driverId: String,
    
    @SerializedName("givenName")
    val givenName: String,
    
    @SerializedName("familyName")
    val familyName: String,
    
    @SerializedName("code")
    val code: String? = null,
    
    @SerializedName("permanentNumber")
    val permanentNumber: String? = null
)

data class SprintConstructor(
    @SerializedName("constructorId")
    val constructorId: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("nationality")
    val nationality: String? = null
)

data class SprintTime(
    @SerializedName("time")
    val time: String
)

data class SprintFastestLap(
    @SerializedName("lap")
    val lap: String,
    
    @SerializedName("Time")
    val time: SprintTime
)
