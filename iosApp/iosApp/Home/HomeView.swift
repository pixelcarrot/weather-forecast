import SwiftUI
import CoreLocation
import shared

struct HomeView: View {
    
    @ObservedObject var viewModel: HomeViewModel
    
    var body: some View {
        ZStack {
            switch viewModel.state {
            case .idle:
                ProgressView()
                    .onAppear(perform: {
                        debugPrint("onAppear")
                        viewModel.dispatch(action: .load)
                    })
            case .loading:
                ProgressView()
            case .loaded(let weather, let image):
                WeatherView(weather: weather, image: image)
            case .failed:
                VStack {
                    Text("Sorry, Unfortunately, an error occurred. Please try again later.")
                        .multilineTextAlignment(.center)
                        .padding()
                    Button(action: {
                        viewModel.dispatch(action: .load)
                    }, label: {
                        Image(systemName: "arrow.clockwise.circle")
                            .resizable()
                            .frame(width: 32.0, height: 32.0)
                    })
                }
            }
        }
        .onReceive(NotificationCenter.default.publisher(for: UIApplication.willEnterForegroundNotification)) { _ in
            debugPrint("willEnterForegroundNotification")
            viewModel.dispatch(action: .load)
        }
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var viewModel = HomeViewModel()
    static var background = "https://images.unsplash.com/photo-1629875900852-8ea865ec48f5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80"
    
    static var previews: some View {
        Group {
            HomeView(viewModel: viewModel)
                .onAppear(perform: {
                    viewModel.state = .loaded(
                        weather: Weather(
                            city: "City",
                            country: "Country",
                            temperature: 30.0,
                            feelsLike: 28.0,
                            condition: "Broken Clouds"
                        ),
                        image: WeatherImage(
                            imageUrl: background,
                            author: "Justin Nguyen",
                            authorUrl: "https://unsplash.com/@pixelcarrot"
                        )
                    )
                })
        }
    }
}
