package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    // Int - number of the day, List of data - it consists of 24 entries. Each entry is data for specific hour of the day
    val currentWeatherData: WeatherData?,
    // API returns 7 days ин default???
    val cityName: String? = null
)