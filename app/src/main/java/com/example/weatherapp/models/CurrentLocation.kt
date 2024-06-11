package com.example.weatherapp.models

import java.io.Serializable

data class CurrentLocation(
    var cod: String?,
    var count: Int?,
    val list: ArrayList<ListA> = arrayListOf(),
    var message: String?
)

data class Clouds(
    var all: Int?
)

data class Coord(
    val lat: Double,
    val lon: Double
)
data class Main(
    var feels_like: Double?,
    var grnd_level: Int?,
    var humidity: Int?,
    var pressure: Int?,
    var sea_level: Int?,
    var temp: Double,
    var temp_max: Double?,
    var temp_min: Double?
)
data class Sys(
    var country: String?
)

data class Weather(
    var description: String,
    var icon: String?,
    var id: Int?,
    val main: String
)
data class Wind(
    var deg: Int?,
    var speed: Double?
)


data class ListA (
    var clouds: Clouds?,
    var coord: Coord?,
    var dt: Int?,
    var id: Int?,
    val main: Main,
    var name: String,
    var rain: Any?,
    var snow: Any?,
    var sys: Sys?,
    val weather: ArrayList<Weather> = arrayListOf(),
    var wind: Wind?
):Serializable