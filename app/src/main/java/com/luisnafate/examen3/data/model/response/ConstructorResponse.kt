package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName
import com.luisnafate.examen3.data.model.Constructor

/**
 * Response wrapper para constructores de la API Ergast
 */
data class ConstructorResponse(
    @SerializedName("MRData")
    val mrData: MRData = MRData()
) {
    data class MRData(
        @SerializedName("ConstructorTable")
        val constructorTable: ConstructorTable = ConstructorTable()
    )
    
    data class ConstructorTable(
        @SerializedName("Constructors")
        val constructors: List<Constructor> = emptyList()
    )
    
    fun getConstructors(): List<Constructor> {
        return mrData.constructorTable.constructors
    }
}
