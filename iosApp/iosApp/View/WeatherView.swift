import SwiftUI
import shared

struct WeatherView: View {
    
    var weather: Weather
    
    var body: some View {
        VStack(alignment: .center) {
            Text("\(weather.city), \(weather.country)")
                .font(.system(size: 32, design: .default))
                .fontWeight(.light)
            Text("\(weather.condition.capitalized)")
                .font(.system(size: 16, design: .default))
                .fontWeight(.light)
            Text("\(String(format: "%.0f", weather.temperature))°")
                .font(.system(size: 64, design: .default))
                .fontWeight(.thin)
            Text("Feels like \(String(format: "%.0f", weather.feelsLike))°")
                .font(.system(size: 16, design: .default))
                .fontWeight(.light)
        }
    }
}

struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        WeatherView(weather: Weather(city: "Ho Chi Minh City", country: "VN", temperature: 30.5, feelsLike: 28.0, condition: "clear sky", icon: ""))
            .previewLayout(PreviewLayout.sizeThatFits)
    }
}
