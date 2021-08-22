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
    static var previews: some View {
        Group {
            HomeView(viewModel: HomeViewModel())
        }
    }
}
