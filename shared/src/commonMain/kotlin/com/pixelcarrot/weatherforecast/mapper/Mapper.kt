package com.pixelcarrot.weatherforecast.mapper

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.model.WeatherImage
import com.pixelcarrot.weatherforecast.service.unsplash.response.UnsplashResponse
import com.pixelcarrot.weatherforecast.service.weather.response.OpenWeatherResponse

fun OpenWeatherResponse.toWeatherModel() = Weather(
    city = name,
    country = sys.country,
    temperature = main.temp,
    feelsLike = main.feelsLike,
    condition = weather.firstOrNull()?.description.orEmpty(),
    icon = weather.firstOrNull()?.icon.orEmpty(),
)

fun UnsplashResponse.toWeatherImageModel() = WeatherImage(
    imageUrl = urls.regular,
    author = user.name,
    authorUrl = user.links.html
)
