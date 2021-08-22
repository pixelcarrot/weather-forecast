package com.pixelcarrot.weatherforecast.service.weather

import com.pixelcarrot.weatherforecast.Constant
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class WeatherMapServiceImpl(private val client: HttpClient) : WeatherService {

    override suspend fun getWeather(lat: Double, lon: Double): OpenWeatherResponse {
        return withContext(Dispatchers.Default) {
            val response: String = client.get {
                url("https://api.openweathermap.org/data/2.5/weather")
                parameter("lat", lat.toString())
                parameter("lon", lon.toString())
                parameter("units", "metric")
                parameter("APPID", Constant.OPEN_WEATHER_MAP_API_KEY)
            }
            json.decodeFromString(response)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

}
