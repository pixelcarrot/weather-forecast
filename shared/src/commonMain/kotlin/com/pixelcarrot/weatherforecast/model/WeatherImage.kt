package com.pixelcarrot.weatherforecast.model

data class WeatherImage(
    val imageUrl: String = "",
    val author: String = "",
    val authorUrl: String = "",
) {
    companion object Factory {
        fun default() = WeatherImage() // Swift does not support Kotlin Default Argument
    }
}
