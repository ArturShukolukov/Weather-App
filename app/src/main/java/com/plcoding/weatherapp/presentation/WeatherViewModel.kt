package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.TimeZone
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
) : ViewModel() {

    var state by mutableStateOf(WeatherState())

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val location = locationTracker.getCurrentLocation()
            if (location != null) {


                val result =
                    repository.getWeatherData(location.latitude, location.longitude, TimeZone.getDefault())
                state = when (result) {
                    is Resource.Success -> state.copy(
                        weatherInfo = result.data,
                        isLoading = false,
                        error = null
                    )

                    is Resource.Error -> state.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            } else {
                state = state.copy(
                    isLoading = false,
                    error = "Could not retrieve location"
                )
            }
        }
    }
}