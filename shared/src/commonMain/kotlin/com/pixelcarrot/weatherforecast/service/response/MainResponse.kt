package com.pixelcarrot.weatherforecast.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    @SerialName("temp")
    val temp: Double = 0.0,

    @SerialName("feels_like")
    val feelsLike: Double = 0.0,

    @SerialName("temp_min")
    val tempMin: Double = 0.0,

    @SerialName("temp_max")
    val tempMax: Double = 0.0,

    @SerialName("pressure")
    val pressure: Double = 0.0,

    @SerialName("humidity")
    val humidity: Double = 0.0,
)
