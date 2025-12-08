package com.luisnafate.examen3.data.model

import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("season") val season: String = "",
    @SerializedName("round") val round: String = "",
    @SerializedName("raceName") val raceName: String = "",
    @SerializedName("Circuit") val circuit: Circuit = Circuit(),
    @SerializedName("date") val date: String = "",
    @SerializedName("time") val time: String? = null,
    @SerializedName("url") val url: String = ""
) {
    val uniqueId: String
        get() = "$season-$round"
}
