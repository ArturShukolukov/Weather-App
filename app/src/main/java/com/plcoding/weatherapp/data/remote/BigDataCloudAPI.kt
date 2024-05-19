package com.plcoding.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BigDataCloudAPI {
    @GET("data/reverse-geocode-client")
    suspend fun getCityName(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("localityLanguage") localityLanguage: String = "en"
    ): BigDataCloudResponse
}

data class BigDataCloudResponse(
    val city: String?
)