package com.example.week_4_assignment.data.model

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val `1h`: Double?
)