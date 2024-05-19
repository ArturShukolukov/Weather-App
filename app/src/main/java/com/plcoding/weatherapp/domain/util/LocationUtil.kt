package com.plcoding.weatherapp.domain.util

import android.content.Context
import android.location.Geocoder
import java.util.*

fun getTimeZoneFromLocation(context: Context, latitude: Double, longitude: Double): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
    val address = addresses?.firstOrNull()
    return address?.let {
        val timeZone = TimeZone.getDefault()
        timeZone.id
    } ?: TimeZone.getDefault().id
}