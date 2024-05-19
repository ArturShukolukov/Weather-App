package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.TimeZone

interface WeatherAPI {

    @GET("v1/forecast?hourly=temperature_2m,weathercode&daily=temperature_2m_max,temperature_2m_min,weathercode")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("forecast_days") forecastDays: Int = 10,
        @Query("timezone") timezone: TimeZone
    ) : WeatherDTO
}