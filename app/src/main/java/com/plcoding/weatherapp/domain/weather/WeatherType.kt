package com.plcoding.weatherapp.domain.weather

import com.plcoding.weatherapp.R


// Cоздадим extension функцию, которая принимает ID и возвращает соответствующий элемент enum
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
