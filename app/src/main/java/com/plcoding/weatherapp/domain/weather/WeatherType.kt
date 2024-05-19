package com.plcoding.weatherapp.domain.weather

import com.plcoding.weatherapp.R


// Cоздадим extension функцию, которая принимает ID и возвращает соответствующий элемент enum
//inline fun <reified T : Enum<T>> enumFromId(id: Int): T? where T : Enum<T>, T : EnumId {
inline fun <reified T> enumFromId(id: Int): T? where T : Enum<T>, T : EnumId {
    return enumValues<T>().find { it.getWeatherTypeId() == id }
}

// Для того, чтобы наша функция работала с любым enum, который имеет ID, создадим интерфейс
interface EnumId {
    fun getWeatherTypeId(): Int
}


enum class WeatherType(val id: Int, val drawableResId: Int) : EnumId {
    CLEAR_SKY(0, R.drawable.ic_sunny),
    MAINLY_CLEAR(1, R.drawable.ic_cloudy),
    PARTLY_CLOUDY(2, R.drawable.ic_cloudy),
    OVERCAST(3, R.drawable.ic_cloudy),
    FOGGY(45, R.drawable.ic_very_cloudy),
    DEPOSITING_RIME_FOG(48, R.drawable.ic_very_cloudy),
    LIGHT_DRIZZLE(51, R.drawable.ic_rainshower),
    MODERATE_DRIZZLE(53, R.drawable.ic_rainshower),
    DENSE_DRIZZLE(55, R.drawable.ic_rainshower),
    LIGHT_FREEZING_DRIZZLE(56, R.drawable.ic_snowyrainy),
    DENSE_FREEZING_DRIZZLE(57, R.drawable.ic_snowyrainy),
    SLIGHT_RAIN(61, R.drawable.ic_rainy),
    MODERATE_RAIN(62, R.drawable.ic_rainy),
    HEAVY_RAIN(63, R.drawable.ic_rainy),
    LIGHT_FREEZING_RAIN(66, R.drawable.ic_snowyrainy),
    HEAVY_FREEZING_RAIN(67, R.drawable.ic_snowyrainy),
    SLIGHT_SNOWFALL(71, R.drawable.ic_snowy),
    MODERATE_SNOWFALL(73, R.drawable.ic_heavysnow),
    HEAVY_SNOWFALL(75, R.drawable.ic_heavysnow),
    SNOW_GRAINS(77, R.drawable.ic_heavysnow),
    SLIGHT_RAIN_SHOWERS(80, R.drawable.ic_rainshower),
    MODERATE_RAIN_SHOWERS(81, R.drawable.ic_rainshower),
    VIOLENT_RAIN_SHOWERS(82, R.drawable.ic_rainshower),
    SLIGHT_SNOW_SHOWERS(85, R.drawable.ic_snowy),
    HEAVY_SNOW_SHOWERS(86, R.drawable.ic_snowy),
    MODERATE_THUNDERSTORM(95, R.drawable.ic_thunder),
    SLIGHT_HAIL_THUNDERSTORM(96, R.drawable.ic_rainythunder),
    HEAVY_HAIL_THUNDERSTORM(99, R.drawable.ic_rainythunder);

    override fun getWeatherTypeId(): Int = id

}

//
//sealed class WeatherType(
//    val weatherDesc: String,
//    @DrawableRes val iconRes: Int
//) {
//    object ClearSky : WeatherType(
//        weatherDesc = "Clear sky",
//        iconRes = R.drawable.ic_sunny
//    )
//    object MainlyClear : WeatherType(
//        weatherDesc = "Mainly clear",
//        iconRes = R.drawable.ic_cloudy
//    )
//    object PartlyCloudy : WeatherType(
//        weatherDesc = "Partly cloudy",
//        iconRes = R.drawable.ic_cloudy
//    )
//    object Overcast : WeatherType(
//        weatherDesc = "Overcast",
//        iconRes = R.drawable.ic_cloudy
//    )
//    object Foggy : WeatherType(
//        weatherDesc = "Foggy",
//        iconRes = R.drawable.ic_very_cloudy
//    )
//    object DepositingRimeFog : WeatherType(
//        weatherDesc = "Depositing rime fog",
//        iconRes = R.drawable.ic_very_cloudy
//    )
//    object LightDrizzle : WeatherType(
//        weatherDesc = "Light drizzle",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object ModerateDrizzle : WeatherType(
//        weatherDesc = "Moderate drizzle",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object DenseDrizzle : WeatherType(
//        weatherDesc = "Dense drizzle",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object LightFreezingDrizzle : WeatherType(
//        weatherDesc = "Slight freezing drizzle",
//        iconRes = R.drawable.ic_snowyrainy
//    )
//    object DenseFreezingDrizzle : WeatherType(
//        weatherDesc = "Dense freezing drizzle",
//        iconRes = R.drawable.ic_snowyrainy
//    )
//    object SlightRain : WeatherType(
//        weatherDesc = "Slight rain",
//        iconRes = R.drawable.ic_rainy
//    )
//    object ModerateRain : WeatherType(
//        weatherDesc = "Rainy",
//        iconRes = R.drawable.ic_rainy
//    )
//    object HeavyRain : WeatherType(
//        weatherDesc = "Heavy rain",
//        iconRes = R.drawable.ic_rainy
//    )
//    object HeavyFreezingRain: WeatherType(
//        weatherDesc = "Heavy freezing rain",
//        iconRes = R.drawable.ic_snowyrainy
//    )
//    object SlightSnowFall: WeatherType(
//        weatherDesc = "Slight snow fall",
//        iconRes = R.drawable.ic_snowy
//    )
//    object ModerateSnowFall: WeatherType(
//        weatherDesc = "Moderate snow fall",
//        iconRes = R.drawable.ic_heavysnow
//    )
//    object HeavySnowFall: WeatherType(
//        weatherDesc = "Heavy snow fall",
//        iconRes = R.drawable.ic_heavysnow
//    )
//    object SnowGrains: WeatherType(
//        weatherDesc = "Snow grains",
//        iconRes = R.drawable.ic_heavysnow
//    )
//    object SlightRainShowers: WeatherType(
//        weatherDesc = "Slight rain showers",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object ModerateRainShowers: WeatherType(
//        weatherDesc = "Moderate rain showers",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object ViolentRainShowers: WeatherType(
//        weatherDesc = "Violent rain showers",
//        iconRes = R.drawable.ic_rainshower
//    )
//    object SlightSnowShowers: WeatherType(
//        weatherDesc = "Light snow showers",
//        iconRes = R.drawable.ic_snowy
//    )
//    object HeavySnowShowers: WeatherType(
//        weatherDesc = "Heavy snow showers",
//        iconRes = R.drawable.ic_snowy
//    )
//    object ModerateThunderstorm: WeatherType(
//        weatherDesc = "Moderate thunderstorm",
//        iconRes = R.drawable.ic_thunder
//    )
//    object SlightHailThunderstorm: WeatherType(
//        weatherDesc = "Thunderstorm with slight hail",
//        iconRes = R.drawable.ic_rainythunder
//    )
//    object HeavyHailThunderstorm: WeatherType(
//        weatherDesc = "Thunderstorm with heavy hail",
//        iconRes = R.drawable.ic_rainythunder
//    )
//
//
//
//    companion object {
//        fun fromWMO(code: Int): WeatherType {
//            return when(code) {
//                0 -> ClearSky
//                1 -> MainlyClear
//                2 -> PartlyCloudy
//                3 -> Overcast
//                45 -> Foggy
//                48 -> DepositingRimeFog
//                51 -> LightDrizzle
//                53 -> ModerateDrizzle
//                55 -> DenseDrizzle
//                56 -> LightFreezingDrizzle
//                57 -> DenseFreezingDrizzle
//                61 -> SlightRain
//                63 -> ModerateRain
//                65 -> HeavyRain
//                66 -> LightFreezingDrizzle
//                67 -> HeavyFreezingRain
//                71 -> SlightSnowFall
//                73 -> ModerateSnowFall
//                75 -> HeavySnowFall
//                77 -> SnowGrains
//                80 -> SlightRainShowers
//                81 -> ModerateRainShowers
//                82 -> ViolentRainShowers
//                85 -> SlightSnowShowers
//                86 -> HeavySnowShowers
//                95 -> ModerateThunderstorm
//                96 -> SlightHailThunderstorm
//                99 -> HeavyHailThunderstorm
//                else -> ClearSky
//            }
//        }
//    }
//}