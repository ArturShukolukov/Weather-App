package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.DailyWeatherDataDTO
import com.plcoding.weatherapp.data.remote.HourlyWeatherDataDTO
import com.plcoding.weatherapp.data.remote.WeatherDTO
//import com.plcoding.weatherapp.data.remote.WeatherDataDTO
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.domain.weather.enumFromId
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//private data class IndexedWeatherData(
//    val index: Int,
//    val data : WeatherData
//)
//fun WeatherDataDTO.toWeatherDataMap(): Map<Int, List<WeatherData>> {
//    return time.mapIndexed{index, time ->
//        val temperatures = temperatures[index]
//        val weatherCode = weatherCodes[index]
//        IndexedWeatherData(
//            index = index,
//            data = WeatherData(
//                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
//                temperatureCelsius = temperatures,
//                weatherType = enumFromId<WeatherType>(weatherCode) ?: WeatherType.CLEAR_SKY
//                // enum can output null so I guess I have to provide default value
//            )
//        )
//
//    }.groupBy{
//        it.index / 24 // get's the current day I guess?
//    }.mapValues {
//        it.value.map{
//            it.data
//        } // represents list of weather data
//    }
//}
//
//fun WeatherDTO.toWeatherInfo(): WeatherInfo {
//    val weatherDataMap = weatherData.toWeatherDataMap()
//    val now = LocalDateTime.now()
//
//    val currentWeatherData = if (now.hour == 23 && now.minute >= 30) {
//        // If the current time is close to midnight, get the forecast for the next day
//        weatherDataMap[1]?.find {
//            it.time.hour == 0
//        }
//    } else {
//        // Otherwise, get the closest forecast for the current day and hour
//        val hour = if (now.minute < 30) now.hour else now.hour + 1
//        weatherDataMap[0]?.find {
//            it.time.hour == hour
//        }
//    }
//
//    return WeatherInfo(
//        weatherDataPerDay = weatherDataMap,
//        currentWeatherData = currentWeatherData
//    )
//}

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






