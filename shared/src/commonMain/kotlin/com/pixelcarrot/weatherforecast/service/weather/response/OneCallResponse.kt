package com.pixelcarrot.weatherforecast.service.weather.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OneCallResponse(
    @SerialName("lat")
    val lat: Double = 0.0,

    @SerialName("lon")
    val lon: Double = 0.0,

    @SerialName("timezone")
    val timezone: String = "",

    @SerialName("current")
    val current: ForecastResponse = ForecastResponse(),
)
