package com.example.week_4_assignment.data.model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("dew_point")
    val dew_point: Double?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("feels_like")
    val feels_like: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("snow")
    val snow: Snow?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("uvi")
    val uvi: Double?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: List<WeatherX>?,
    @SerializedName("wind_deg")
    val wind_deg: Int?,
    @SerializedName("wind_gust")
    val wind_gust: Double?,
    @SerializedName("wind_speed")
    val wind_speed: Double?
)