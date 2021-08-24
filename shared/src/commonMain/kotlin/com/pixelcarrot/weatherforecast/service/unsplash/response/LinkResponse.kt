package com.pixelcarrot.weatherforecast.service.unsplash.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkResponse(
    @SerialName("html")
    val html: String = "",
)
