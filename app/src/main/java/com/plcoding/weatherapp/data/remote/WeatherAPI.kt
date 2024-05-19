package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //TODO FORECAST FOR 10 DAYS
    //v1/forecast?daily=temperature_2m_max,temperature_2m_min,weathercode&timezone=YOUR_TIMEZONE&forecast_days=10 //TODO CHECK
    @GET("v1/forecast?hourly=temperature_2m,weathercode")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ) : WeatherDTO
}