package com.plcoding.weatherapp.domain.weather

import com.plcoding.weatherapp.R


// Cоздадим extension функцию, которая принимает ID и возвращает соответствующий элемент enum
//inline fun <reified T : Enum<T>> enumFromId(id: Int): T? where T : Enum<T>, T : EnumId {
inline fun <reified T> enumFromId(id: Int): T? where T : Enum<T>, T : EnumId {
    return enumValues<T>().find { it.getId() == id }
}

// Для того, чтобы наша функция работала с любым enum, который имеет ID, создадим интерфейс
interface EnumId {
    fun getId(): Int
}

enum class WeatherType(val id: Int) : EnumId {
    CLEAR_SKY(0),
    MAINLY_CLEAR(1),
    PARTLY_CLOUDY(2),
    OVERCAST(3),
    FOGGY(45),
    DEPOSITING_RIME_FOG(48),
    LIGHT_DRIZZLE(51),
    MODERATE_DRIZZLE(53),
    DENSE_DRIZZLE(55),
    LIGHT_FREEZING_DRIZZLE(56),
    DENSE_FREEZING_DRIZZLE(57),
    SLIGHT_RAIN(61),
    MODERATE_RAIN(62),
    HEAVY_RAIN(63),
    LIGHT_FREEZING_RAIN(66),
    HEAVY_FREEZING_RAIN(67),
    SLIGHT_SNOWFALL(71),
    MODERATE_SNOWFALL(73),
    HEAVY_SNOWFALL(75),
    SNOW_GRAINS(77),
    SLIGHT_RAIN_SHOWERS(80),
    MODERATE_RAIN_SHOWERS(81),
    VIOLENT_RAIN_SHOWERS(82),
    SLIGHT_SNOW_SHOWERS(85),
    HEAVY_SNOW_SHOWERS(86),
    MODERATE_THUNDERSTORM(95),
    SLIGHT_HAIL_THUNDERSTORM(96),
    HEAVY_HAIL_THUNDERSTORM(99);

    override fun getId(): Int = id

    fun getDrawableResId(): Int {
        return when (this) {
            CLEAR_SKY -> R.drawable.ic_sunny
            MAINLY_CLEAR -> R.drawable.ic_cloudy
            PARTLY_CLOUDY -> R.drawable.ic_cloudy
            OVERCAST -> R.drawable.ic_cloudy
            FOGGY -> R.drawable.ic_very_cloudy
            DEPOSITING_RIME_FOG -> R.drawable.ic_very_cloudy
            LIGHT_DRIZZLE -> R.drawable.ic_rainshower
            MODERATE_DRIZZLE -> R.drawable.ic_rainshower
            DENSE_DRIZZLE -> R.drawable.ic_rainshower
            LIGHT_FREEZING_DRIZZLE -> R.drawable.ic_snowyrainy
            DENSE_FREEZING_DRIZZLE -> R.drawable.ic_snowyrainy
            SLIGHT_RAIN -> R.drawable.ic_rainy
            MODERATE_RAIN -> R.drawable.ic_rainy
            HEAVY_RAIN -> R.drawable.ic_rainy
            LIGHT_FREEZING_RAIN -> R.drawable.ic_snowyrainy
            HEAVY_FREEZING_RAIN -> R.drawable.ic_snowyrainy
            SLIGHT_SNOWFALL -> R.drawable.ic_snowy
            MODERATE_SNOWFALL -> R.drawable.ic_heavysnow
            HEAVY_SNOWFALL -> R.drawable.ic_heavysnow
            SNOW_GRAINS -> R.drawable.ic_heavysnow
            SLIGHT_RAIN_SHOWERS -> R.drawable.ic_rainshower
            MODERATE_RAIN_SHOWERS -> R.drawable.ic_rainshower
            VIOLENT_RAIN_SHOWERS -> R.drawable.ic_rainshower
            SLIGHT_SNOW_SHOWERS -> R.drawable.ic_snowy
            HEAVY_SNOW_SHOWERS -> R.drawable.ic_snowy
            MODERATE_THUNDERSTORM -> R.drawable.ic_thunder
            SLIGHT_HAIL_THUNDERSTORM -> R.drawable.ic_rainythunder
            HEAVY_HAIL_THUNDERSTORM -> R.drawable.ic_rainythunder
        }
    }
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