import SwiftUI

@available(iOS 14.0, *)
@available(iOS 14.0, *)
struct ContentView: View {

    init(){
        let largeTitleFontAttrs = [ NSAttributedString.Key.font: UIFont(name: "avenir", size: 24)
        ]
        let smallTitleFontAttrs = [ NSAttributedString.Key.font: UIFont(name: "avenir", size: 20)
        ]
        
        UINavigationBar.appearance().titleTextAttributes = smallTitleFontAttrs
        UINavigationBar.appearance().largeTitleTextAttributes = largeTitleFontAttrs
    }
    
    var body: some View {
        RecipeListScreen()
    }
}

@available(iOS 14.0, *)
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
