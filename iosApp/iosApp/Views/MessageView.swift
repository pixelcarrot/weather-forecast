import SwiftUI

struct MessageView: View {
    
    let message: String
    
    var body: some View {
        Text(message)
    }
}

struct MessageView_Previews: PreviewProvider {
    static var previews: some View {
        MessageView(message: "Sunflower Weather")
            .previewLayout(.sizeThatFits)
    }
}
