package com.pixelcarrot.weatherforecast.feature.home.viewmodel

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.model.WeatherImage

sealed class HomeState {
    object Idle : HomeState()

    object Loading : HomeState()

    data class Loaded(
        val weather: Weather,
        val weatherImage: WeatherImage,
    ) : HomeState()

    data class Failed(
        val message: String
    ) : HomeState()
}

// a different approach to make loading indicator overlay a current content
//
//data class HomeState(
//    val status: Status = Status.Idle,
//    val weather: Weather = Weather(),
//    val weatherImage: WeatherImage = WeatherImage(),
//    val message: String = "",
//)
//
//enum class Status {
//    Idle,
//    Loading,
//    Loaded,
//    Failed
//}
