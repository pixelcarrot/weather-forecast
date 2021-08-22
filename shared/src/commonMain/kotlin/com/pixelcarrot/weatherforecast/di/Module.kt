package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.Constant
import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.repository.UnsplashRepository
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashService
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashServiceImpl
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashServiceMock
import com.pixelcarrot.weatherforecast.service.weather.WeatherMapServiceImpl
import com.pixelcarrot.weatherforecast.service.weather.WeatherService
import com.pixelcarrot.weatherforecast.service.weather.WeatherServiceMock
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import com.pixelcarrot.weatherforecast.usecase.GetImageUseCase
import io.ktor.client.*
import io.ktor.client.features.logging.*

class Module {

    val platform: Platform by lazy {
        Platform()
    }

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
            WeatherServiceMock(platform)
        } else {
            WeatherMapServiceImpl(client)
        }
    }

    val unsplash: UnsplashService by lazy {
        if (Constant.IS_MOCK_ENABLED) {
            UnsplashServiceMock(platform)
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
