struct OpenWeatherResponse: Decodable {
    var weather: [WeatherResponse]
    var main: MainResponse
    var sys: SysResponse
    var name: String
}
