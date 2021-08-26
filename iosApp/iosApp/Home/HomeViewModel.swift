import SwiftUI
import Combine

class HomeViewModel: ObservableObject {
    
    @Published var state: HomeState = .idle
    
    fileprivate let locationManager = LocationManager()
    fileprivate var disposables = Set<AnyCancellable>()
    
    func dispatch(action: HomeAction) {
        switch action {
        case .load:
            checkLocation()
            break
        case .noLocation:
            requestLocation()
            break
        case .receiveLocation(let lat, let lon):
            checkWeather(lat: lat, lon: lon)
            break
        case .noPermission:
            state = .failed(NSError())
            break
        }
    }
    
    fileprivate func requestLocation() {
        locationManager.$lastLocation
            .first(where: { location in
                location != nil
            })
            .receive(on: DispatchQueue.main)
            .sink { location in
                guard let coordinate = location?.coordinate else {
                    return
                }
                self.dispatch(action: .receiveLocation(lat: coordinate.latitude, lon: coordinate.longitude))
            }
            .store(in: &disposables)
    }
    
    fileprivate func checkLocation() {
        locationManager.$locationStatus
            .receive(on: DispatchQueue.main)
            .sink(receiveValue: { status in
                switch status {
                case .notDetermined:
                    // no need: iOS already prompt alert permission
                    break
                case .authorizedAlways, .authorizedWhenInUse:
                    guard let coordinate = self.locationManager.lastLocation?.coordinate else {
                        self.dispatch(action: .noLocation)
                        return
                    }
                    self.dispatch(action: .receiveLocation(lat: coordinate.latitude, lon: coordinate.longitude))
                    break
                case .restricted, .denied:
                    self.dispatch(action: .noPermission)
                    break
                default:
                    self.state = .failed(NSError())
                    break
                }
            })
            .store(in: &disposables)
    }
    
    fileprivate func checkWeather(lat: Double, lon: Double) {
        state = .loading
        
        var weather: Weather
        if let response = loadJson(OpenWeatherResponse.self, filename: "OpenWeather.json") {
            weather = Weather(
                city: response.name,
                country: response.sys.country,
                temperature: response.main.temp,
                feelsLike: response.main.feels_like,
                condition: response.weather.first?.description ?? ""
            )
        } else {
            weather = Weather()
        }
        
        var image: WeatherImage
        if let response = loadJson(UnsplashResponse.self, filename: "Unsplash.json") {
            image = WeatherImage(
                imageUrl: response.urls.regular,
                author: response.user.name,
                authorUrl: response.links.html
            )
        } else {
            image = WeatherImage()
        }
        
        state = .loaded(weather: weather, image: image)
    }
    
    fileprivate func loadJson<T: Decodable>(_ t: T.Type, filename fileName: String) -> T? {
        if let url = Bundle.main.url(forResource: fileName, withExtension: "") {
            do {
                let data = try Data(contentsOf: url)
                let decoder = JSONDecoder()
                return try decoder.decode(T.self, from: data)
            } catch {
                print("error:\(error)")
            }
        }
        return nil
    }
    
}
