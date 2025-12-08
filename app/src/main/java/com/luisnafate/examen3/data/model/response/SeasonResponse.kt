package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName

data class SeasonResponse(
    @SerializedName("MRData")
    val mrData: SeasonMRData
)

data class SeasonMRData(
    @SerializedName("SeasonTable")
    val seasonTable: SeasonTable
)

data class SeasonTable(
    @SerializedName("Seasons")
    val seasons: List<Season>
)

data class Season(
    @SerializedName("season")
    val season: String,
    
    @SerializedName("url")
    val url: String? = null
)
