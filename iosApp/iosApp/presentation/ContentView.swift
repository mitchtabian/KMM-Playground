import SwiftUI

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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
