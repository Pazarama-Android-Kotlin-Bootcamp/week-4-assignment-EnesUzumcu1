package com.example.week_4_assignment.data.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("daily")
    val daily: List<Daily>?,
    @SerializedName("hourly")
    val hourly: List<Hourly>?,
    @SerializedName("lat")
    val lat: Int?,
    @SerializedName("lon")
    val lon: Int?,
    @SerializedName("minutely")
    val minutely: List<Minutely>?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezone_offset: Int?
)