package com.pixelcarrot.weatherforecast

internal object Constant {

    // Disable mock required to register API KEY
    const val IS_MOCK_ENABLED = true

    val OPEN_WEATHER_MAP_API_KEY: String by lazy {
        TODO("Please register an API KEY at https://home.openweathermap.org/users/sign_up")
    }

}
