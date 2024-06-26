package com.example.weatherapp.userinterface

import com.example.weatherapp.models.CurrentLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/find")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int,
        @Query("appid") apiKey: String
    ): Call<CurrentLocation>
}
//    @GET("data/2.5/find")

//    fun getCurrentWeather(
//        @Query("q") location: String,
//        @Query("appid") apiKey: String,
//        @Query("units") units: String = "metric"
//    ): Call<CurrentLocation>
//}