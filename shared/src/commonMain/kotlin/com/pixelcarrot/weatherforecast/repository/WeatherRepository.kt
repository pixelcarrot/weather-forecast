package com.pixelcarrot.weatherforecast.repository

import com.pixelcarrot.weatherforecast.mapper.toWeatherModel
import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.service.weather.WeatherService

class WeatherRepository(
    private val service: WeatherService,
) {

    suspend fun getWeather(lat: Double, lon: Double): Weather {
        return service.getWeather(lat, lon).toWeatherModel()
    }

}
