enum HomeState {
    case idle
    case loading
    case loaded(weather: Weather, image: WeatherImage)
    case failed(Error)
}
