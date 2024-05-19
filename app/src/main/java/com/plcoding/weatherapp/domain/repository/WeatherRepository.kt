package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import java.util.TimeZone

/**
 * This method retrieves weather data for a specified location and timezone.
 *
 * @param lat The latitude of the location for which weather data is requested.
 * @param long The longitude of the location for which weather data is requested.
 * @param timezone The timezone of the location to ensure accurate local time-based weather data.
 * @return A [Resource] containing [WeatherInfo] which includes current weather data and a 10-day forecast.
 * The [Resource] can represent a successful data fetch or an error.
 **/
interface WeatherRepository {
    suspend fun getWeatherData(
        lat: Double,
        long: Double,
        timezone: TimeZone
    ): Resource<WeatherInfo>
}