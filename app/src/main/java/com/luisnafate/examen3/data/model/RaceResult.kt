package com.luisnafate.examen3.data.model

import com.google.gson.annotations.SerializedName

data class RaceResult(
    @SerializedName("position") val position: String = "",
    @SerializedName("positionText") val positionText: String = "",
    @SerializedName("points") val points: String = "",
    @SerializedName("Driver") val driver: Driver = Driver(),
    @SerializedName("Constructor") val constructor: Constructor = Constructor(),
    @SerializedName("grid") val grid: String = "",
    @SerializedName("laps") val laps: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("Time") val time: Time? = null,
    @SerializedName("FastestLap") val fastestLap: FastestLap? = null
)

data class Time(
    @SerializedName("millis") val millis: String? = null,
    @SerializedName("time") val time: String = ""
)

data class FastestLap(
    @SerializedName("rank") val rank: String = "",
    @SerializedName("lap") val lap: String = "",
    @SerializedName("Time") val time: Time = Time(),
    @SerializedName("AverageSpeed") val averageSpeed: AverageSpeed? = null
)

data class AverageSpeed(
    @SerializedName("units") val units: String = "",
    @SerializedName("speed") val speed: String = ""
)
