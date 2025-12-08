package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.Race

data class RaceResponse(
    @SerializedName("MRData") val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("RaceTable") val raceTable: RaceTable = RaceTable()
    )
    
    data class RaceTable(
        @SerializedName("season") val season: String? = null,
        @SerializedName("Races") val races: List<Race> = emptyList()
    )
}
