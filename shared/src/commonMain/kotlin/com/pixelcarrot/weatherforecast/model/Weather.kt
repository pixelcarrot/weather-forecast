package com.pixelcarrot.weatherforecast.model

data class Weather(
    val city: String = "",
    val country: String = "",
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val condition: String = "",
    val icon: String = "",
) {
    companion object Factory {
        fun default() = Weather() // Swift does not support Kotlin Default Argument
    }
}
