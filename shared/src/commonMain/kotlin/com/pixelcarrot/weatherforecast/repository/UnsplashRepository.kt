package com.pixelcarrot.weatherforecast.repository

import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashService

class UnsplashRepository(
    private val service: UnsplashService,
) {

    suspend fun getImage(query: String): String {
        return service.getImage(query).urls.regular
    }

}
