package com.example.week_4_assignment.data.model

import com.google.gson.annotations.SerializedName

data class Alert(
    @SerializedName("alerts")
    val alerts: List<AlertX>?
)