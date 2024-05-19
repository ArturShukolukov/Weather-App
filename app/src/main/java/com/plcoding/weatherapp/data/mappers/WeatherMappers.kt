package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.DailyWeatherDataDTO
import com.plcoding.weatherapp.data.remote.HourlyWeatherDataDTO
import com.plcoding.weatherapp.data.remote.WeatherDTO
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.domain.weather.enumFromId
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun HourlyWeatherDataDTO.toHourlyWeatherDataList(): List<WeatherData> {
    return times.mapIndexed { index, time ->
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperatures[index],
            weatherType = enumFromId<WeatherType>(weatherCodes[index]) ?: WeatherType.CLEAR_SKY
        )
    }
}

fun DailyWeatherDataDTO.toDailyWeatherDataList(): List<WeatherData> {
    return times.mapIndexed { index, time ->
        WeatherData(
            time = LocalDate.parse(time, DateTimeFormatter.ISO_DATE).atStartOfDay(),  // Converts LocalDate to LocalDateTime at start of day
            temperatureCelsius = (minTemperatures[index] + maxTemperatures[index]) / 2,
            weatherType = enumFromId<WeatherType>(weatherCodes[index]) ?: WeatherType.CLEAR_SKY
        )
    }
}

fun WeatherDTO.toWeatherInfo(): WeatherInfo {
    val hourlyWeatherDataList = hourlyWeatherData.toHourlyWeatherDataList()
    val dailyWeatherDataList = dailyWeatherData.toDailyWeatherDataList()

    val now = LocalDateTime.now()
    val currentWeatherData = hourlyWeatherDataList.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        hourlyWeatherData = hourlyWeatherDataList,
        dailyWeatherData = dailyWeatherDataList,
        currentWeatherData = currentWeatherData
    )
}






