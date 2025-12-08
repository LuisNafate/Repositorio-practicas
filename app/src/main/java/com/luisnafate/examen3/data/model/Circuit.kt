package com.luisnafate.examen3.data.model

import com.google.gson.annotations.SerializedName

data class Circuit(
    @SerializedName("circuitId") val circuitId: String = "",
    @SerializedName("circuitName") val circuitName: String = "",
    @SerializedName("Location") val location: Location = Location()
)

data class Location(
    @SerializedName("lat") val lat: String = "",
    @SerializedName("long") val long: String = "",
    @SerializedName("locality") val locality: String = "",
    @SerializedName("country") val country: String = ""
)
