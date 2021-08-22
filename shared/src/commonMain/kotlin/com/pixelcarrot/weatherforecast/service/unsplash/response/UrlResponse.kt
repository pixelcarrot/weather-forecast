package com.pixelcarrot.weatherforecast.service.unsplash.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrlResponse(
    @SerialName("raw")
    val raw: String = "",

    @SerialName("full")
    val full: String = "",

    @SerialName("regular")
    val regular: String = "",

    @SerialName("thumb")
    val thumb: String = "",
)
