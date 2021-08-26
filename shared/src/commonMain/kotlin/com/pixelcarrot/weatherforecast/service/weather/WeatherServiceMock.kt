package com.pixelcarrot.weatherforecast.service.weather

import com.pixelcarrot.weatherforecast.asset.AssetManager
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class WeatherServiceMock(
    private val assetManager: AssetManager,
) : WeatherService {

    override suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse {
        return withContext(Dispatchers.Default) {
            delay(2000)
            json.decodeFromString(response)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    private val response
        get() = assetManager.loadAsset(MOCK)

    companion object {
        private const val MOCK = "OpenWeather.json"
    }

}
