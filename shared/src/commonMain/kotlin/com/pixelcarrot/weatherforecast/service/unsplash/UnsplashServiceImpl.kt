package com.pixelcarrot.weatherforecast.service.unsplash

import com.pixelcarrot.weatherforecast.Constant
import com.pixelcarrot.weatherforecast.service.unsplash.response.UnsplashResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class UnsplashServiceImpl(private val client: HttpClient) : UnsplashService {

    override suspend fun getImage(query: String): UnsplashResponse {
        return withContext(Dispatchers.Default) {
            val response: String = client.get {
                url("https://api.unsplash.com/photos/random")
                header("Authorization", "Client-ID ${Constant.UNSPLASH_CLIENT_ID}")
                parameter("query", query)
                parameter("content_filter", "high") // Limit results by content safety
            }
            json.decodeFromString(response)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

}
