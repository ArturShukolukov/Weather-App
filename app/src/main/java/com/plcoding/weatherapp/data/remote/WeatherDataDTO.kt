package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDTO(
    //TODO 10 DAY FORECAST???
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
)
