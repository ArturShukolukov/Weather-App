package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    data: WeatherData,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today ${
                    data.time.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                }",
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = data.weatherType.drawableResId),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${data.temperatureCelsius}°C",
                fontSize = 50.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

            }
        }
    }
}