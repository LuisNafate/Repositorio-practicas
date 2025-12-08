package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Response wrapper para tiempos de vuelta de la API Ergast
 */
data class LapTimesResponse(
    @SerializedName("MRData")
    val mrData: MRData
) {
    data class MRData(
        @SerializedName("RaceTable")
        val raceTable: RaceTable
    )
    
    data class RaceTable(
        @SerializedName("season")
        val season: String,
        @SerializedName("round")
        val round: String,
        @SerializedName("Races")
        val races: List<Race>
    )
    
    data class Race(
        @SerializedName("season")
        val season: String,
        @SerializedName("round")
        val round: String,
        @SerializedName("raceName")
        val raceName: String,
        @SerializedName("Laps")
        val laps: List<Lap>
    )
    
    data class Lap(
        @SerializedName("number")
        val number: String,
        @SerializedName("Timings")
        val timings: List<Timing>
    )
    
    data class Timing(
        @SerializedName("driverId")
        val driverId: String,
        @SerializedName("position")
        val position: String,
        @SerializedName("time")
        val time: String
    )
    
    fun getLaps(): List<Lap> {
        return mrData.raceTable.races.firstOrNull()?.laps ?: emptyList()
    }
}
