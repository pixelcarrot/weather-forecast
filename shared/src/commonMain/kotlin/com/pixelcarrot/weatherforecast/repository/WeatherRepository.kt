package com.pixelcarrot.weatherforecast.repository

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.service.WeatherService
import com.pixelcarrot.weatherforecast.service.response.OpenWeatherResponse

class WeatherRepository(
    private val service: WeatherService,
) {

    suspend fun getWeather(lat: Double, lon: Double): Weather {
        return service.getWeather(lat, lon).toWeatherModel()
    }

}

private fun OpenWeatherResponse.toWeatherModel() = Weather(
    city = name,
    country = sys.country,
    temperature = main.temp,
    feelsLike = main.feelsLike,
    condition = weather.firstOrNull()?.description.orEmpty(),
    icon = weather.firstOrNull()?.icon.orEmpty(),
)
