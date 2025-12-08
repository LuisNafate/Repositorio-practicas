package com.luisnafate.examen3.data.model

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("driverId") val driverId: String = "",
    @SerializedName("permanentNumber") val permanentNumber: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("givenName") val givenName: String = "",
    @SerializedName("familyName") val familyName: String = "",
    @SerializedName("dateOfBirth") val dateOfBirth: String = "",
    @SerializedName("nationality") val nationality: String = "",
    @SerializedName("url") val url: String = ""
) {
    val fullName: String
        get() = "${givenName.trim()} ${familyName.trim()}".trim()
}
