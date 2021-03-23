// Source:
// https://stackoverflow.com/questions/60677622/how-to-display-image-from-a-url-in-swiftui

import Combine
import SwiftUI

class ImageLoader: ObservableObject {
    
    @Published var data: Data = Data()

    init(urlString:String) {
        guard let url = URL(string: urlString) else { return }
        let task = URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data else { return }
            DispatchQueue.main.async {
                self.data = data
            }
        }.resume()
    }
}
