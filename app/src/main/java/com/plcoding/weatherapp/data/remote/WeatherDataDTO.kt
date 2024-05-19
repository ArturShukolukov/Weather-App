package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json


data class HourlyWeatherDataDTO(
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,

    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,

    @field:Json(name = "time")
    val times: List<String>
)

data class DailyWeatherDataDTO(
    @field:Json(name = "temperature_2m_max")
    val maxTemperatures: List<Double>,

    @field:Json(name = "temperature_2m_min")
    val minTemperatures: List<Double>,

    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,

    @field:Json(name = "time")
    val times: List<String>
)