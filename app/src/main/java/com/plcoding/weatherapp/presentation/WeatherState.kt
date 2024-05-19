package com.plcoding.weatherapp.presentation

import com.plcoding.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val cityName: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
