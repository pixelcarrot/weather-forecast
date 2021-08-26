package com.pixelcarrot.weatherforecast.di

import com.pixelcarrot.weatherforecast.mock.Mock
import com.pixelcarrot.weatherforecast.mock.MockService
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import io.ktor.client.*

class TestModule {

    val mock: Mock by lazy {
        Mock()
    }

    val client: HttpClient by lazy {
        HttpClient()
    }

    val service: MockService by lazy {
        MockService()
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(service)
    }

    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase by lazy {
        GetCurrentWeatherUseCase(weatherRepository)
    }

}
