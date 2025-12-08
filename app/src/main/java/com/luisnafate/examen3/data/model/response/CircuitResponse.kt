package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.Circuit

data class CircuitResponse(
    @SerializedName("MRData") val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("CircuitTable") val circuitTable: CircuitTable = CircuitTable()
    )
    
    data class CircuitTable(
        @SerializedName("Circuits") val circuits: List<Circuit> = emptyList()
    )
}
