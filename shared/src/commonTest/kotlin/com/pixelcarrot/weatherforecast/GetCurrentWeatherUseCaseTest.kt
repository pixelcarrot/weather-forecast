package com.pixelcarrot.weatherforecast

import com.pixelcarrot.weatherforecast.di.TestModule
import com.pixelcarrot.weatherforecast.mapper.toWeatherModel
import com.pixelcarrot.weatherforecast.mock.Mock
import com.pixelcarrot.weatherforecast.mock.MockService
import com.pixelcarrot.weatherforecast.repository.WeatherRepository
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import com.pixelcarrot.weatherforecast.util.runBlockingTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class GetCurrentWeatherUseCaseTest {

    private lateinit var mock: Mock
    private lateinit var service: MockService
    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @BeforeTest
    fun before() {
        val module = TestModule()
        mock = module.mock
        service = module.service
        repository = module.weatherRepository
        useCase = module.getCurrentWeatherUseCase
    }

    @Test
    fun remoteSuccess_getCurrentWeather_shouldReturnWeather() {
        service.mockGetWeather = mock::createOpenWeatherResponse

        runBlockingTest {
            // Given
            val response = mock.createOpenWeatherResponse(Mock.SAIGON_COORDINATES)

            // When
            val (lat, lon) = Mock.SAIGON_COORDINATES
            val result = useCase.execute(lat, lon)

            // Then
            assertEquals(response.toWeatherModel(), result)
        }
    }

    @Test
    fun remoteSuccess_getCurrentWeather_passingIncorrectCoordinates() {
        service.mockGetWeather = mock::createOpenWeatherResponse

        runBlockingTest {
            // Given
            val response = mock.createOpenWeatherResponse(Mock.SAIGON_COORDINATES)

            // When
            val (lat, lon) = Mock.SAIGON_COORDINATES
            val result = useCase.execute(lon, lat)

            // Then
            assertNotEquals(response.toWeatherModel(), result)
        }
    }

    @Test
    fun remoteFailure_getCurrentWeather_error() {
        // Given
        val response = RuntimeException()
        service.mockGetWeather = { _ -> throw response }

        runBlockingTest {
            // When
            val result = try {
                useCase.execute(0.0, 0.0)
            } catch (ex: Exception) {
                ex
            }

            // Then
            assertEquals(response, result)
        }
    }

}
