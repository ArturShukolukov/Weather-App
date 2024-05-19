package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

//data class WeatherDTO(
//    @field:Json(name = "hourly")
//    val weatherData: WeatherDataDTO
//)
data class WeatherDTO(
    @field:Json(name = "hourly")
    val hourlyWeatherData: HourlyWeatherDataDTO,
    @field:Json(name = "daily")
    val dailyWeatherData: DailyWeatherDataDTO
)