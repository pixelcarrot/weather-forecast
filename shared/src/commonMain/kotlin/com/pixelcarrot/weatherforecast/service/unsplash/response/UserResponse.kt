package com.pixelcarrot.weatherforecast.service.unsplash.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("name")
    val name: String = "",

    @SerialName("links")
    val links: LinkResponse = LinkResponse(),
)
