import SwiftUI
import CoreLocation
import shared

struct HomeView: View {
    
    @ObservedObject var viewModel: HomeViewModel
    
    var body: some View {
        switch viewModel.state {
        case .idle:
            MessageView(message: "Idle")
                .onAppear(perform: {
                    viewModel.dispatch(event: .load)
                })
        case .loading:
            MessageView(message: "Loading")
        case .loaded(let weather, let background):
            WeatherView(weather: weather, background: background)
        case .failed:
            MessageView(message: "Failed")
        }
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var viewModel = HomeViewModel()
    static var background = "https://images.unsplash.com/photo-1561074557-5a1c929e7535?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=668&q=80"
    
    static var previews: some View {
        Group {
            HomeView(viewModel: viewModel)
                .onAppear(perform: {
                    viewModel.state = .loaded(weather: Weather(city: "City", country: "Country", temperature: 30.0, feelsLike: 28.0, condition: "Broken Clouds", icon: ""), background: background)
                })
        }
    }
}
