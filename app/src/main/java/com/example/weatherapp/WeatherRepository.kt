package com.example.weatherapp

import android.location.Location
import com.example.weatherapp.models.CurrentLocation

class WeatherRepository {
    class WeatherRepository {

         suspend fun fetchCurrentData(location: Location, tempStatus: Boolean) :CurrentLocation?{

            val endUrl ="weather?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=$weather_api_key"

            val currentModel =WeatherApi.weatherServiceApi.getCurrentWeather(endUrl)
            return currentModel
        }
    }

}