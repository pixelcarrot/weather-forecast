package com.pixelcarrot.weatherforecast.feature.home.viewmodel

sealed class HomeAction {
    object Load : HomeAction()
    data class ReceiveLocation(val coordinates: Pair<Double, Double>) : HomeAction()
}
