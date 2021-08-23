package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.mock.Mock
import com.pixelcarrot.weatherforecast.mock.MockService
import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.repository.UnsplashRepository
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashService
import com.pixelcarrot.weatherforecast.service.unsplash.UnsplashServiceMock
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import com.pixelcarrot.weatherforecast.usecase.GetImageUseCase
import io.ktor.client.*

class TestModule {

    val mock: Mock by lazy {
        Mock()
    }

    val platform: Platform by lazy {
        Platform()
    }

    val client: HttpClient by lazy {
        HttpClient()
    }

    val service: MockService by lazy {
        MockService()
    }

    val unsplash: UnsplashService by lazy {
        UnsplashServiceMock(platform)
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(service)
    }

    val unsplashRepository: UnsplashRepository by lazy {
        UnsplashRepository(unsplash)
    }

    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase by lazy {
        GetCurrentWeatherUseCase(weatherRepository)
    }

    val getImageUseCase: GetImageUseCase by lazy {
        GetImageUseCase(unsplashRepository)
    }

}
