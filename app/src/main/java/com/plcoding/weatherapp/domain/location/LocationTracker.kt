package com.plcoding.weatherapp.domain.location

import android.location.Location // TODO May need to be replaced with custom location class

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}