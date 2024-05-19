package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import com.plcoding.weatherapp.data.remote.BigDataCloudAPI
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.data.remote.WeatherDTO
import com.plcoding.weatherapp.data.remote.WeatherDataDTO
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val bigDataCloudAPI: BigDataCloudAPI
): WeatherRepository {
        override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
            return try {
//                Resource.Success(
//                    data = api.getWeatherData(
//                    lat = lat,
//                    long = long
//                    ).toWeatherInfo()
//                )
                val weatherDTO = weatherAPI.getWeatherData(lat, long)
                val weatherInfo = weatherDTO.toWeatherInfo()

                // Fetch city name
                val cityResponse = bigDataCloudAPI.getCityName(lat, long)
                val cityName = cityResponse.city ?: "Unknown"

                // Update weatherInfo with the city name
                val updatedWeatherInfo = weatherInfo.copy(cityName = cityName)

                Resource.Success(data = updatedWeatherInfo)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: "Unknown error")
            }
        }
    }