package com.luisnafate.examen3.data.model.response

import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("MRData")
    val mrData: StatusMRData
)

data class StatusMRData(
    @SerializedName("StatusTable")
    val statusTable: StatusTable
)

data class StatusTable(
    @SerializedName("Status")
    val statusList: List<Status>
)

data class Status(
    @SerializedName("statusId")
    val statusId: String,
    
    @SerializedName("count")
    val count: String,
    
    @SerializedName("status")
    val status: String
)
