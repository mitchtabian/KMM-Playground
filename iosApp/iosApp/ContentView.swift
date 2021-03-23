import SwiftUI
import shared

struct ContentView: View {

    @ObservedObject var imageLoader: ImageLoader
    @State var image: UIImage = UIImage()
    
    let imageUrl = "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/3/featured_image.png"
    
    init() {
        imageLoader = ImageLoader(urlString: imageUrl)
    }
    
    var body: some View {
        GeometryReader.init(content: { geometry in
            Image(uiImage: image)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width:geometry.size.width)
                .onReceive(imageLoader.$data){ data in
                    self.image = UIImage(data: data) ?? UIImage()
                }
        })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
