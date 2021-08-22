package com.pixelcarrot.weatherforecast.service.unsplash

import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.service.unsplash.response.UnsplashResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class UnsplashServiceMock(
    private val platform: Platform,
) : UnsplashService {

    override suspend fun getImage(query: String): UnsplashResponse {
        return withContext(Dispatchers.Default) {
            json.decodeFromString(response)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    private val response
        get() = platform.loadAsset(MOCK)

    companion object {
        private const val MOCK = "Unsplash.json"
    }

}
