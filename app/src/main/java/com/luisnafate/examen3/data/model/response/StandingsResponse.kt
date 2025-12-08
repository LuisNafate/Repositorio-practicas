package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.Constructor
import com.luisnafate.examen3.data.model.DriverStanding

data class StandingsResponse(
    @SerializedName("MRData") val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("StandingsTable") val standingsTable: StandingsTable = StandingsTable()
    )
    
    data class StandingsTable(
        @SerializedName("season") val season: String = "",
        @SerializedName("StandingsLists") val standingsLists: List<StandingsList> = emptyList()
    )
    
    data class StandingsList(
        @SerializedName("season") val season: String = "",
        @SerializedName("round") val round: String = "",
        @SerializedName("DriverStandings") val driverStandings: List<DriverStanding>? = null,
        @SerializedName("ConstructorStandings") val constructorStandings: List<ConstructorStanding>? = null
    )
    
    data class ConstructorStanding(
        @SerializedName("position") val position: String = "",
        @SerializedName("positionText") val positionText: String = "",
        @SerializedName("points") val points: String = "",
        @SerializedName("wins") val wins: String = "",
        @SerializedName("Constructor") val constructor: Constructor = Constructor()
    )
}
