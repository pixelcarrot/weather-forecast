import SwiftUI
import Combine
import shared

class HomeViewModel: ObservableObject {
    
    @Published var state: HomeState = .idle
    
    fileprivate let locationManager = LocationManager()
    fileprivate let module = Module()
    fileprivate var disposables = Set<AnyCancellable>()
    
    fileprivate let getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    fileprivate let getImageUseCase: GetImageUseCase
    
    init() {
        getCurrentWeatherUseCase = module.getCurrentWeatherUseCase
        getImageUseCase = module.getImageUseCase
    }
    
    func dispatch(event: HomeEvent) {
        switch event {
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
                self.dispatch(event: .receiveLocation(lat: coordinate.latitude, lon: coordinate.longitude))
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
                        self.dispatch(event: .noLocation)
                        return
                    }
                    self.dispatch(event: .receiveLocation(lat: coordinate.latitude, lon: coordinate.longitude))
                    break
                case .restricted, .denied:
                    self.dispatch(event: .noPermission)
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
                    .map { imageUrl in
                        (weather, imageUrl)
                    }
            }
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: {
                debugPrint("fetch \($0)")
            }, receiveValue: { [weak self] weather, imageUrl in
                self?.state = .loaded(weather: weather, background: imageUrl)
            })
            .store(in: &disposables)
    }
    
    fileprivate func getWeatherByLocation(lat: Double, lon: Double) -> Future<Weather, Error> {
        return Future<Weather, Error>() { [weak self] promise in
            self?.getCurrentWeatherUseCase.execute(lat: lat, lon: lon) { result, error in
                if let error = error {
                    promise(.failure(error))
                } else {
                    promise(.success(result ?? Weather.Factory().createWeather()))
                }
            }
        }
    }
    
    fileprivate func getImageByKeyword(keyword: String) -> Future<String, Never> {
        return Future<String, Never>() { [weak self] promise in
            self?.getImageUseCase.execute(query: keyword, completionHandler: { result, error in
                if let _ = error {
                    promise(.success(""))
                } else {
                    promise(.success(result ?? ""))
                }
            })
        }
    }
    
}
