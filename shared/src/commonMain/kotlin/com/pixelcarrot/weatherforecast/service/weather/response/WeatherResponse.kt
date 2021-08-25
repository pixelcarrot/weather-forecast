package com.pixelcarrot.weatherforecast.service.weather.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("main")
    val main: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("icon")
    val icon: String = "",
)
