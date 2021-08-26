package com.pixelcarrot.weatherforecast.mock

import com.pixelcarrot.weatherforecast.service.weather.response.CountryResponse
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse
import com.pixelcarrot.weatherforecast.service.weather.response.WeatherResponse

class Mock {

    fun createOpenWeatherResponse(coordinates: Pair<Double, Double>) = when (coordinates) {
        SAIGON_COORDINATES -> SAIGON_WEATHER
        else -> OpenWeatherResponse()
    }

    companion object {
        val SAIGON_COORDINATES = 10.7546664 to 106.4150317

        val SAIGON_WEATHER = OpenWeatherResponse(
            weather = listOf(
                WeatherResponse(
                    id = 804,
                    main = "Clouds",
                    description = "overcast clouds",
                )
            ),
            name = "Ho Chi Minh City",
            sys = CountryResponse(
                country = "VN"
            )
        )

    }

}
