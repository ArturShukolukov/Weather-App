package com.plcoding.weatherapp.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    // Forward the status or result from the API
    // If response is Succesful, return data
    // If there was an error we get a message we can extract in the viewmodel
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
