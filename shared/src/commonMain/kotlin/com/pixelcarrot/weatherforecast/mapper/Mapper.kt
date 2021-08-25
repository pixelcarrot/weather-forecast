package com.pixelcarrot.weatherforecast.mapper

import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.model.WeatherImage
import com.pixelcarrot.weatherforecast.service.unsplash.response.UnsplashResponse
import com.pixelcarrot.weatherforecast.service.weather.response.OneCallResponse

fun OneCallResponse.toWeatherModel() = Weather(
    city = timezone.split("/").getOrNull(1).orEmpty().replace("_", " "),
    country = "",
    temperature = current.temp,
    feelsLike = current.feelsLike,
    condition = current.weather.firstOrNull()?.description.orEmpty(),
    icon = current.weather.firstOrNull()?.icon.orEmpty()
)

fun UnsplashResponse.toWeatherImageModel() = WeatherImage(
    imageUrl = urls.regular,
    author = user.name,
    authorUrl = user.links.html
)
