import SwiftUI
import Combine
import shared

class HomeViewModel: ObservableObject {
    
    @Published var weather: Weather = Weather.Factory().createWeather()
    @Published var background: String = ""
    
    private let module = Module()
    private var disposables = Set<AnyCancellable>()
    
    private let getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private let getImageUseCase: GetImageUseCase
    
    init() {
        getCurrentWeatherUseCase = module.getCurrentWeatherUseCase
        getImageUseCase = module.getImageUseCase
    }
    
    func checkWeather() {
        getWeatherByLocation(lat: 10.7956098, lon: 106.6356215)
            .flatMap{ result in
                self.getImageByKeyword(keyword: result.condition)
                    .map { imageUrl in
                        (result, imageUrl)
                    }
            }
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: {
                debugPrint("fetch \($0)")
            }, receiveValue: { [weak self] result, imageUrl in
                self?.weather = result
                self?.background = imageUrl
            })
            .store(in: &disposables)
    }
    
    private func getWeatherByLocation(lat: Double, lon: Double) -> Future<Weather, Error> {
        return Future<Weather, Error>() { [weak self] promise in
            self?.getCurrentWeatherUseCase.execute(lat: 10.7956098, lon: 106.6356215) { result, error in
                if let error = error {
                    promise(.failure(error))
                } else {
                    promise(.success(result ?? Weather.Factory().createWeather()))
                }
            }
        }
    }
    
    private func getImageByKeyword(keyword: String) -> Future<String, Never> {
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
