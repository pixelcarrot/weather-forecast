package com.pixelcarrot.weatherforecast.repository

import com.pixelcarrot.weatherforecast.mapper.toWeatherImageModel
import com.pixelcarrot.weatherforecast.model.WeatherImage
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashService

class UnsplashRepository(
    private val service: UnsplashService,
) {

    suspend fun getImage(query: String): WeatherImage {
        return service.getImage(query).toWeatherImageModel()
    }

}
