package com.pixelcarrot.weatherforecast.usecase

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository,
) {

    suspend fun execute(lat: Double, lon: Double): Weather {
        return weatherRepository.getWeather(lat, lon)
    }

}
