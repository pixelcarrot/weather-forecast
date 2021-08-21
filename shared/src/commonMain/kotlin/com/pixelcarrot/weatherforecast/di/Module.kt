package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.Constant
import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.service.MockWeatherService
import com.pixelcarrot.weatherforecast.service.OpenWeatherMapService
import com.pixelcarrot.weatherforecast.service.WeatherService
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import io.ktor.client.*

class Module {

    val platform: Platform by lazy {
        Platform()
    }

    val client: HttpClient by lazy {
        HttpClient()
    }

    val service: WeatherService by lazy {
        if (Constant.IS_MOCK_ENABLED) {
            MockWeatherService(platform)
        } else {
            OpenWeatherMapService(client)
        }
    }

    val repository: WeatherRepository by lazy {
        WeatherRepository(service)
    }

    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase by lazy {
        GetCurrentWeatherUseCase(repository)
    }

}
