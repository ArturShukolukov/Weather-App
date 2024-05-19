package com.plcoding.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.plcoding.weatherapp.data.remote.BigDataCloudAPI
import com.plcoding.weatherapp.data.remote.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun providerFusedLocationProviderClient(app: Application): FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideBigDataCloudAPI(): BigDataCloudAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.bigdatacloud.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BigDataCloudAPI::class.java)
    }
}