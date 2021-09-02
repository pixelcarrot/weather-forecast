import SwiftUI
import Combine
import shared

class HomeViewModel: ObservableObject {
    
    @Published var state: HomeState = .idle
    
    fileprivate let locationManager = LocationManager()
    
    fileprivate let module = AppModule(context: AppContext())
    fileprivate var disposables = Set<AnyCancellable>()
    
    fileprivate let getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    fileprivate let getImageUseCase: GetImageUseCase
    
    init() {
        getCurrentWeatherUseCase = module.getCurrentWeatherUseCase
        getImageUseCase = module.getImageUseCase
    }
    
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
        getWeatherByLocation(lat: lat, lon: lon)
            .flatMap{ weather in
                self.getImageByKeyword(keyword: weather.condition)
                    .map { image in
                        (weather, image)
                    }
            }
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: {
                debugPrint("fetch \($0)")
            }, receiveValue: { [weak self] weather, image in
                self?.state = .loaded(weather: weather, image: image)
            })
            .store(in: &disposables)
    }
    
    fileprivate func getWeatherByLocation(lat: Double, lon: Double) -> Future<Weather, Error> {
        return Future<Weather, Error>() { [weak self] promise in
            self?.getCurrentWeatherUseCase.execute(lat: lat, lon: lon) { result, error in
                if let error = error {
                    promise(.failure(error))
                } else {
                    promise(.success(result ?? Weather.Factory().default()))
                }
            }
        }
    }
    
    fileprivate func getImageByKeyword(keyword: String) -> Future<WeatherImage, Never> {
        return Future<WeatherImage, Never>() { [weak self] promise in
            self?.getImageUseCase.execute(query: keyword, completionHandler: { result, error in
                promise(.success(result ?? WeatherImage.Factory().default()))
            })
        }
    }
    
}
