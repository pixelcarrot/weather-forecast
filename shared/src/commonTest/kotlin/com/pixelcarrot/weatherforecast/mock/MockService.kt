package com.pixelcarrot.weatherforecast.mock

import com.pixelcarrot.weatherforecast.service.weather.WeatherService
import com.pixelcarrot.weatherforecast.service.weather.response.OneCallResponse

class MockService : WeatherService {

    lateinit var mockGetWeather: (coordinates: Pair<Double, Double>) -> OneCallResponse

    override suspend fun getWeather(lat: Double, lon: Double): OneCallResponse {
        return mockGetWeather(lat to lon)
    }

}
