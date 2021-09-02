package com.pixelcarrot.weatherforecast.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.pixelcarrot.weatherforecast.AppContext
import com.pixelcarrot.weatherforecast.compose.FailedView
import com.pixelcarrot.weatherforecast.compose.LoadingView
import com.pixelcarrot.weatherforecast.compose.WeatherView
import com.pixelcarrot.weatherforecast.di.AppModule
import com.pixelcarrot.weatherforecast.feature.home.viewmodel.HomeAction
import com.pixelcarrot.weatherforecast.feature.home.viewmodel.HomeEvent
import com.pixelcarrot.weatherforecast.feature.home.viewmodel.HomeState
import com.pixelcarrot.weatherforecast.feature.home.viewmodel.HomeViewModel
import com.pixelcarrot.weatherforecast.ui.theme.SunflowerTheme

class HomeActivity : ComponentActivity() {

    private val module: AppModule by lazy { AppModule(AppContext(applicationContext)) }

    private val viewModel: HomeViewModel by lazy {
        HomeViewModel(
            module.getCurrentWeatherUseCase,
            module.getImageUseCase,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this, ::onStateChanged)
        viewModel.event.observe(this, ::onSideEffect)
    }

    private fun onStateChanged(state: HomeState) {
        setContent {
            SunflowerTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background) {
                    when (state) {
                        HomeState.Idle -> LoadingView()
                        HomeState.Loading -> LoadingView()
                        is HomeState.Loaded -> WeatherView(state.weather, state.weatherImage)
                        is HomeState.Failed -> FailedView(state.message, onRetry = {
                            viewModel.dispatch(HomeAction.Load)
                        })
                    }
                }
            }
        }
    }

    private fun onSideEffect(event: HomeEvent) {
        // TODO: Toast? or anything that does not touch compose setContent
    }

    override fun onResume() {
        super.onResume()
        viewModel.dispatch(HomeAction.Load)
    }

}
