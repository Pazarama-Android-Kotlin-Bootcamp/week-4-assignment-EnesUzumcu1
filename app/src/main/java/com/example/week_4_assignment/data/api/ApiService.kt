package com.example.week_4_assignment.data.api

import com.example.week_4_assignment.data.model.Weather
import com.example.week_4_assignment.data.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//apide queryler ayarlandi
interface ApiService {
    @GET(Constants.ONEALL)
    fun getEvertyhingWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon : Double,
        @Query("lang") lang: String,
        @Query("units") units: String,
    ): Call<Weather>
}