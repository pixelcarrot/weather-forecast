package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.platform.Platform
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.service.MockWeatherService
import com.pixelcarrot.weatherforecast.service.WeatherService
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase

class Module {

    val platform: Platform by lazy {
        Platform()
    }

    val service: WeatherService by lazy {
        MockWeatherService(platform)
    }

    val repository: WeatherRepository by lazy {
        WeatherRepository(service)
    }

    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase by lazy {
        GetCurrentWeatherUseCase(repository)
    }

}
