package com.example.chooserecipientcompose.data.api

import com.example.chooserecipientcompose.data.remote.model.CustomerProfileDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/api/customer-profile")
    suspend fun getCustomerProfileDto(): CustomerProfileDto

    companion object {
        val BASE_URL = "http://10.0.0.91:8080"

        val service: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}