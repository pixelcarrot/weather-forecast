package com.pixelcarrot.weatherforecast.usecase

import com.pixelcarrot.weatherforecast.model.WeatherImage
import com.pixelcarrot.weatherforecast.repository.UnsplashRepository

class GetImageUseCase(
    private val unsplashRepository: UnsplashRepository,
) {

    suspend fun execute(query: String): WeatherImage {
        return unsplashRepository.getImage(query)
    }

}
