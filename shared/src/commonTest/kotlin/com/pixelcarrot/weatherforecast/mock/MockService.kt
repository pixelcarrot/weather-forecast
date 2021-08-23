package com.pixelcarrot.weatherforecast.mock

import com.pixelcarrot.weatherforecast.service.weather.WeatherService
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse

class MockService : WeatherService {

    lateinit var mockGetWeather: (coordinates: Pair<Double, Double>) -> OpenWeatherResponse

    override suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse {
        return mockGetWeather(lat to lon)
    }

}
