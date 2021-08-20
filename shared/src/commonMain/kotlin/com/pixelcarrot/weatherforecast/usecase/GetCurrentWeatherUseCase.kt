package com.pixelcarrot.weatherforecast.usecase

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository,
) {

    suspend fun getWeatherByLocation(lat: Double, lon: Double): Weather {
        return repository.getWeather(lat, lon)
    }

}
