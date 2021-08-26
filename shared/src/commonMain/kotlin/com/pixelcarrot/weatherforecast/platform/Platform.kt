package com.pixelcarrot.weatherforecast.platform

expect class Platform() {
    fun loadAsset(name: String): String
}