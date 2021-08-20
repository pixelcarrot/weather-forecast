import SwiftUI
import shared

struct HomeView: View {
    
    @ObservedObject var viewModel: HomeViewModel
    
    var body: some View {
        WeatherView(weather: viewModel.weather)
            .onAppear(perform: {
                viewModel.checkWeather()
            })
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            HomeView(viewModel: HomeViewModel())
        }
    }
}
