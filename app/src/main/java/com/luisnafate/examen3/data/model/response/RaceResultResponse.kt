package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.*

data class RaceResultResponse(
    @SerializedName("MRData") val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("RaceTable") val raceTable: RaceTable = RaceTable()
    )
    
    data class RaceTable(
        @SerializedName("season") val season: String = "",
        @SerializedName("round") val round: String = "",
        @SerializedName("Races") val races: List<RaceWithResults> = emptyList()
    )
    
    data class RaceWithResults(
        @SerializedName("season") val season: String = "",
        @SerializedName("round") val round: String = "",
        @SerializedName("raceName") val raceName: String = "",
        @SerializedName("Circuit") val circuit: Circuit = Circuit(),
        @SerializedName("date") val date: String = "",
        @SerializedName("Results") val results: List<RaceResult> = emptyList()
    )
}
