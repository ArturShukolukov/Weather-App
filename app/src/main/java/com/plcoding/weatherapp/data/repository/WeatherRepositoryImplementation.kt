package com.plcoding.weatherapp.data.repository

import android.content.Context
import com.plcoding.weatherapp.data.mappers.toWeatherInfo
//import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import com.plcoding.weatherapp.data.remote.BigDataCloudAPI
import com.plcoding.weatherapp.data.remote.WeatherAPI
//import com.plcoding.weatherapp.data.remote.WeatherDTO
//import com.plcoding.weatherapp.data.remote.WeatherDataDTO
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.util.getTimeZoneFromLocation
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

//class WeatherRepositoryImplementation @Inject constructor(
//    private val weatherAPI: WeatherAPI,
//    private val bigDataCloudAPI: BigDataCloudAPI,
//    private val context: Context
//): WeatherRepository {
//        override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
//            return try {
////                Resource.Success(
////                    data = api.getWeatherData(
////                    lat = lat,
////                    long = long
////                    ).toWeatherInfo()
////                )
//                val timezone = getTimeZoneFromLocation(context, lat, long)
//
//                val weatherDTO = weatherAPI.getWeatherData(lat, long)
//                val weatherInfo = weatherDTO.toWeatherInfo()
//
//                // Fetch city name
//                val cityResponse = bigDataCloudAPI.getCityName(lat, long)
//                val cityName = cityResponse.city ?: "Unknown"
//
//                // Update weatherInfo with the city name
//                val updatedWeatherInfo = weatherInfo.copy(cityName = cityName)
//
//                Resource.Success(data = updatedWeatherInfo)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Resource.Error(e.message ?: "Unknown error")
//            }
//        }
//    }

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val bigDataCloudAPI: BigDataCloudAPI,
    @ApplicationContext private val context: Context // Inject application context
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        timezone: String
    ): Resource<WeatherInfo> {
        return try {
            // Get the user's timezone
            val timezone = getTimeZoneFromLocation(context, lat, long)

            // Fetch weather data with the user's timezone
            val weatherDTO = weatherAPI.getWeatherData(lat, long, timezone = timezone)
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