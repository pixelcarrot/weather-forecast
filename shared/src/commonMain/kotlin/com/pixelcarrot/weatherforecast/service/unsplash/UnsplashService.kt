package com.pixelcarrot.weatherforecast.service.unsplash

import com.pixelcarrot.weatherforecast.service.unsplash.response.UnsplashResponse

interface UnsplashService {

    suspend fun getImage(query: String): UnsplashResponse

}
