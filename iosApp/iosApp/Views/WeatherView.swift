import SwiftUI
import URLImage
import shared

struct WeatherView: View {
    
    let weather: Weather
    let background: String
    
    var body: some View {
        ZStack {
            if let url = URL(string: background) {
                URLImage(url) { _ in 
                    EmptyView()
                } content: { image in
                    image
                        .resizable()
                        .edgesIgnoringSafeArea(.all)
                        .blur(radius: 3.0, opaque: true)
                        .scaledToFill()
                }
            }
            VStack(alignment: .center) {
                if let weather = weather {
                    Text("\(weather.city), \(weather.country)")
                        .font(.system(size: 32, weight: .light, design: .default))
                        .foregroundColor(.white)
                        .padding()
                    
                    Text("\(weather.condition.capitalized)")
                        .font(.system(size: 16, weight: .light, design: .default))
                        .foregroundColor(.white)
                    
                    Spacer()
                    
                    Text("\(String(format: "%.0f", weather.temperature))°")
                        .font(.system(size: 100, weight: .thin, design: .rounded))
                        .foregroundColor(.white)
                        .shadow(radius: 50)
                    
                    Spacer()
                    
                    Text("Feels like \(String(format: "%.0f", weather.feelsLike))°")
                        .font(.system(size: 16, weight: .light, design: .default))
                        .foregroundColor(.white)
                        .padding()
                }
            }
        }
    }
}

struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.black
                .ignoresSafeArea()
            WeatherView(weather: Weather(city: "Ho Chi Minh City", country: "VN", temperature: 30.0, feelsLike: 28.0, condition: "Broken Clouds", icon: ""), background: "")
        }
    }
}
