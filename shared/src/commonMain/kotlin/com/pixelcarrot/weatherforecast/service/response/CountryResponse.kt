package com.pixelcarrot.weatherforecast.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    @SerialName("country")
    val country: String = "",

    @SerialName("sunrise")
    val sunrise: Long = 0L,

    @SerialName("sunset")
    val sunset: Long = 0L,
)
