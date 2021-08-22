package com.pixelcarrot.weatherforecast.service.weather

import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse

interface WeatherService {

    suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse

}
