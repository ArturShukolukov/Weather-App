package com.plcoding.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    private val viewModel: WeatherViewModel by viewModels()
//    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) {
//            viewModel.loadWeatherInfo()
//        }
//        permissionLauncher.launch(arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//        ))
//        setContent {
//            WeatherAppTheme {
//                Box(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(DarkBlue)
//                    ) {
//                        WeatherCard(
//                            state = viewModel.state,
//                            backgroundColor = DeepBlue
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        // WeatherForecast(state = viewModel.state) // weather forecast is not needed for this task
//                        Text(
//                            text = viewModel.state.weatherInfo?.cityName ?: "Loading city...",
//                            color = Color.White,
//                            //style = MaterialTheme.typography.h5,
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                        )
//                    }
//                    if(viewModel.state.isLoading) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                    viewModel.state.error?.let { error ->
//                        Text(
//                            text = error,
//                            color = Color.Red,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Display the city name
                        Text(
                            text = viewModel.state.weatherInfo?.cityName ?: "Loading city...",
                            color = Color.White,
                            //style = MaterialTheme.typography.h5,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Spacer(modifier = Modifier.height(16.dp))
                        // Display the 10-day forecast
                        LazyColumn {
                            items(viewModel.state.weatherInfo?.dailyWeatherData ?: emptyList()) { weatherData ->
                                WeatherForecastItem(weatherData)
                            }
                        }
                    }
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun WeatherForecastItem(weatherData: WeatherData) {

    val dayOfWeek = weatherData.time.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val temperature = String.format("%.1f °C", weatherData.temperatureCelsius)
    val weatherIcon = weatherData.weatherType.drawableResId

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = dayOfWeek,
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = temperature,
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .weight(1f)
        )
    }
}
