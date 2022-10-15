package com.example.week_4_assignment.data.api

import com.example.week_4_assignment.BuildConfig
import com.example.week_4_assignment.data.api.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object{
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService{
            if(!::apiService.isInitialized){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }

        private fun getHttpClient() : OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60,TimeUnit.SECONDS)
            httpClient.readTimeout(60,TimeUnit.SECONDS)
            httpClient.writeTimeout(90,TimeUnit.SECONDS)
            return httpClient.build()
        }
    }
}