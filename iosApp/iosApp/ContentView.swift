import SwiftUI
import shared

struct ContentView: View {

    let recipes = RecipeData.getSearchData()
    
    var body: some View {
        List{
            ForEach(recipes, id: \.self.id){ recipe in
                RecipeCard(recipe: recipe)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
