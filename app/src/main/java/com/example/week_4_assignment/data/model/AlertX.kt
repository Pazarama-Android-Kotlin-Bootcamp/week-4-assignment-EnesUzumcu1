package com.example.week_4_assignment.data.model

import com.google.gson.annotations.SerializedName

data class AlertX(
    @SerializedName("description")
    val description: String?,
    @SerializedName("end")
    val end: Int?,
    @SerializedName("event")
    val event: String?,
    @SerializedName("sender_name")
    val sender_name: String?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("tags")
    val tags: List<String?>?
)