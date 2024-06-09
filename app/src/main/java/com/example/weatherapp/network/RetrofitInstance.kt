package com.example.weatherapp.network

import com.example.weatherapp.userinterface.WeatherApiService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.openweathermap.org/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}

