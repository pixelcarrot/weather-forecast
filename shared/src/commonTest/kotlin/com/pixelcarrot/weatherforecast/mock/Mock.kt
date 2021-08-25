package com.pixelcarrot.weatherforecast.mock

import com.pixelcarrot.weatherforecast.service.weather.response.ForecastResponse
import com.pixelcarrot.weatherforecast.service.weather.response.OneCallResponse
import com.pixelcarrot.weatherforecast.service.weather.response.WeatherResponse

class Mock {

    fun createOpenWeatherResponse(coordinates: Pair<Double, Double>) = when (coordinates) {
        SAIGON_COORDINATES -> SAIGON_WEATHER
        else -> OneCallResponse()
    }

    companion object {
        val SAIGON_COORDINATES = 10.7546664 to 106.4150317

        val SAIGON_WEATHER = OneCallResponse(
            timezone = "Asia/Ho_Chi_Minh",
            current = ForecastResponse(
                weather = listOf(
                    WeatherResponse(
                        id = 804,
                        main = "Clouds",
                        description = "overcast clouds",
                        icon = "04d"
                    )
                )
            )
        )

    }

}
