package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.Driver

data class DriverResponse(
    @SerializedName("MRData") val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("DriverTable") val driverTable: DriverTable = DriverTable()
    )
    
    data class DriverTable(
        @SerializedName("Drivers") val drivers: List<Driver> = emptyList()
    )
}
