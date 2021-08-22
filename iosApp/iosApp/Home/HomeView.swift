import SwiftUI
import URLImage
import shared

struct HomeView: View {
    
    @ObservedObject var viewModel: HomeViewModel
    
    var body: some View {
        ZStack {
            if let url = URL(string: viewModel.background) {
                URLImage(url) { image in
                    image
                        .resizable()
                        .edgesIgnoringSafeArea(.all)
                        .blur(radius: 3.0, opaque: true)
                        .scaledToFill()
                }
            }
            VStack(alignment: .center) {
                if let weather = viewModel.weather {
                    Text("\(weather.city), \(weather.country)")
                        .font(.system(size: 32, weight: .light, design: .default))
                        .padding()
                    
                    Text("\(weather.condition.capitalized)")
                        .font(.system(size: 16, weight: .light, design: .default))
                    
                    Spacer()
                    
                    Text("\(String(format: "%.0f", weather.temperature))°")
                        .font(.system(size: 100, weight: .thin, design: .rounded))
                        .shadow(radius: 50)
                    
                    Spacer()
                    
                    Text("Feels like \(String(format: "%.0f", weather.feelsLike))°")
                        .font(.system(size: 16, weight: .light, design: .default))
                        .padding()
                }
            }
        }
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
