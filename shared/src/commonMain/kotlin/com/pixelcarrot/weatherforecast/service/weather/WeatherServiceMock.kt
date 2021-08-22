package com.pixelcarrot.weatherforecast.service.weather

import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class WeatherServiceMock(
    private val platform: Platform,
) : WeatherService {

    override suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse {
        return withContext(Dispatchers.Default) {
            json.decodeFromString(response)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    private val response
        get() = platform.loadAsset(MOCK)

    companion object {
        private const val MOCK = "OpenWeather.json"
    }

}
