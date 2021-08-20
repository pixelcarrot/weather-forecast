package com.pixelcarrot.weatherforecast.service

import com.pixelcarrot.weatherforecast.service.response.OpenWeatherResponse

interface WeatherService {

    suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse

}
