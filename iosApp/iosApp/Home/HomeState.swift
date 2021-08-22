import shared

enum HomeState {
    case idle
    case loading
    case loaded(weather: Weather, background: String)
    case failed(Error)
}
