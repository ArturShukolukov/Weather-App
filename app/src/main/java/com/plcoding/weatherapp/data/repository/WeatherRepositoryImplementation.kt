package com.plcoding.weatherapp.data.repository

import android.content.Context
import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import com.plcoding.weatherapp.data.remote.BigDataCloudAPI
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.TimeZone
import javax.inject.Inject



class WeatherRepositoryImplementation @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val bigDataCloudAPI: BigDataCloudAPI,
    //@ApplicationContext private val context: Context // Inject application context
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        timezone: TimeZone
    ): Resource<WeatherInfo> {
        return try {
            // Get the user's timezone
            val tzn = TimeZone.getDefault()

            // Fetch weather data with the user's timezone
            val weatherDTO = weatherAPI.getWeatherData(lat, long, timezone = tzn)
            val weatherInfo = weatherDTO.toWeatherInfo()

            // Fetch city name
            val cityResponse = bigDataCloudAPI.getCityName(lat, long)
            val cityName = cityResponse.city ?: "Unknown"

            val updatedWeatherInfo = weatherInfo.copy(cityName = cityName)

            Resource.Success(data = updatedWeatherInfo)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}