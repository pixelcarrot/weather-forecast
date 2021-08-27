package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.Constant
import com.pixelcarrot.weatherforecast.AppContext
import com.pixelcarrot.weatherforecast.asset.AssetManager
import com.pixelcarrot.weatherforecast.repository.UnsplashRepository
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashService
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashServiceImpl
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashServiceMock
import com.pixelcarrot.weatherforecast.service.weather.WeatherServiceImpl
import com.pixelcarrot.weatherforecast.service.weather.WeatherService
import com.pixelcarrot.weatherforecast.service.weather.WeatherServiceMock
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import com.pixelcarrot.weatherforecast.usecase.GetImageUseCase
import io.ktor.client.*
import io.ktor.client.features.logging.*

class AppModule(context: AppContext) {

    val assetManager: AssetManager = context.createAssetManager()

    val client: HttpClient by lazy {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }

    val service: WeatherService by lazy {
        if (Constant.IS_MOCK_ENABLED) {
            WeatherServiceMock(assetManager)
        } else {
            WeatherServiceImpl(client)
        }
    }

    val unsplash: UnsplashService by lazy {
        if (Constant.IS_MOCK_ENABLED) {
            UnsplashServiceMock(assetManager)
        } else {
            UnsplashServiceImpl(client)
        }
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(service)
    }

    val unsplashRepository: UnsplashRepository by lazy {
        UnsplashRepository(unsplash)
    }

    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase by lazy {
        GetCurrentWeatherUseCase(
            weatherRepository,
        )
    }

    val getImageUseCase: GetImageUseCase by lazy {
        GetImageUseCase(
            unsplashRepository,
        )
    }

}
