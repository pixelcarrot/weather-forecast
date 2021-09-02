package com.pixelcarrot.weatherforecast.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.pixelcarrot.weatherforecast.R
import com.pixelcarrot.weatherforecast.model.Weather
import com.pixelcarrot.weatherforecast.model.WeatherImage
import com.pixelcarrot.weatherforecast.ui.theme.SunflowerTheme

@Composable
fun WeatherView(
    weather: Weather,
    image: WeatherImage,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(image.imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (cityText, conditionText, temperatureText, feelsLikeText, author) = createRefs()
            Text(
                text = "${weather.city}, ${weather.country}",
                fontSize = 32.sp,
                modifier = Modifier.constrainAs(cityText) {
                    top.linkTo(parent.top, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text = weather.condition.capitalize(Locale.current),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(conditionText) {
                    top.linkTo(cityText.bottom, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text = "${weather.temperature.toInt()}°",
                fontSize = 100.sp,
                modifier = Modifier.constrainAs(temperatureText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text = stringResource(R.string.feel_like, weather.feelsLike.toInt()),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(feelsLikeText) {
                    bottom.linkTo(author.bottom, 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Box(modifier = Modifier.constrainAs(author) {
                bottom.linkTo(parent.bottom, 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                AuthorView(image.author)
            }
        }
    }
}

@Composable
fun AuthorView(
    author: String,
    onAuthorClicked: () -> Unit = {},
    onUnsplashClicked: () -> Unit = {},
) {
    Row {
        Text(
            text = stringResource(R.string.photo_by),
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = author,
            fontSize = 12.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onAuthorClicked() }
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(R.string.on),
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(R.string.unsplash),
            fontSize = 12.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onUnsplashClicked() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorPreview() {
    SunflowerTheme {
        AuthorView("Justin Nguyen")
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    SunflowerTheme {
        WeatherView(
            Weather(
                city = "Ho Chi Minh City",
                country = "VN",
                temperature = 30.0,
                feelsLike = 28.0,
                condition = "broken clouds"
            ),
            WeatherImage(
                imageUrl = "https://images.unsplash.com/photo-1629875900852-8ea865ec48f5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80",
                authorUrl = "https://pixelcarrot.com/",
                author = "Justin Nguyen",
            )
        )
    }
}
