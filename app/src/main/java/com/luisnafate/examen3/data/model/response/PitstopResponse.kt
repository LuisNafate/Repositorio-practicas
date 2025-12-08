package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName

data class PitstopResponse(
    @SerializedName("MRData")
    val mrData: PitstopMRData
)

data class PitstopMRData(
    @SerializedName("RaceTable")
    val raceTable: PitstopRaceTable
)

data class PitstopRaceTable(
    @SerializedName("Races")
    val races: List<RaceWithPitstops>
)

data class RaceWithPitstops(
    @SerializedName("season")
    val season: String,
    
    @SerializedName("round")
    val round: String,
    
    @SerializedName("raceName")
    val raceName: String,
    
    @SerializedName("PitStops")
    val pitStops: List<PitStop>
)

data class PitStop(
    @SerializedName("driverId")
    val driverId: String,
    
    @SerializedName("lap")
    val lap: String,
    
    @SerializedName("stop")
    val stop: String,
    
    @SerializedName("time")
    val time: String,
    
    @SerializedName("duration")
    val duration: String? = null
)
