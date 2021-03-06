package com.pixelcarrot.weatherforecast.service.unsplash.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashResponse(
    @SerialName("urls")
    val urls: UrlResponse = UrlResponse(),

    @SerialName("user")
    val user: UserResponse = UserResponse(),
)
