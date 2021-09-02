package com.pixelcarrot.weatherforecast.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelcarrot.weatherforecast.usecase.GetCurrentWeatherUseCase
import com.pixelcarrot.weatherforecast.usecase.GetImageUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    private val _event = MutableLiveData<HomeEvent>()
    val event: LiveData<HomeEvent> = _event

    init {
        _state.value = HomeState.Idle
    }

    fun dispatch(action: HomeAction) {
        when (action) {
            is HomeAction.Load -> checkLocation()
            is HomeAction.ReceiveLocation -> load(action.coordinates)
        }
    }

    private fun checkLocation() {
        dispatch(HomeAction.ReceiveLocation(10.7776241 to 106.7029156))
    }

    private fun load(coordinates: Pair<Double, Double>) = viewModelScope.launch {
        _state.value = HomeState.Loading
        try {
            val (lat, lon) = coordinates
            val weather = getCurrentWeatherUseCase.execute(lat, lon)
            val image = getImageUseCase.execute(weather.condition)
            _state.value = HomeState.Loaded(weather, image)
        } catch (ex: Exception) {
            _state.value = HomeState.Failed(ex.message.orEmpty())
        }
    }

}
