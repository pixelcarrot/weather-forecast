import SwiftUI
import Combine
import shared

class HomeViewModel: ObservableObject {
    
    private var disposables = Set<AnyCancellable>()
    private let usecase = Module().getCurrentWeatherUseCase
    
    @Published var weather: Weather = Weather.Factory().createWeather()
    
    func checkWeather() {
        Future<Weather, Error>() { [weak self] promise in
            self?.usecase.getWeatherByLocation(lat: 10.7956098, lon: 106.6356215) { result, error in
                if let error = error {
                    promise(.failure(error))
                } else {
                    promise(.success(result ?? Weather.Factory().createWeather()))
                }
            }
        }
        .receive(on: DispatchQueue.main)
        .sink(receiveCompletion: {
            debugPrint("fetch \($0)")
        }, receiveValue: { [weak self] result in
            self?.weather = result
        })
        .store(in: &disposables)
    }
    
}
