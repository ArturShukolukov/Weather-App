package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val hourlyWeatherData: List<WeatherData>,
    val dailyWeatherData: List<WeatherData>,
    val currentWeatherData: WeatherData?,
    val cityName: String? = null
)