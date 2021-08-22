package com.pixelcarrot.weatherforecast.service.weather.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherResponse(
    @SerialName("id")
    val id: Long = 0L,

    @SerialName("name")
    val name: String = "",

    @SerialName("weather")
    val weather: List<WeatherResponse> = listOf(),

    @SerialName("main")
    val main: MainResponse = MainResponse(),

    @SerialName("sys")
    val sys: CountryResponse = CountryResponse(),
)
