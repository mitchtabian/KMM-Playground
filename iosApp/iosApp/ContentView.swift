import SwiftUI
import shared

struct ContentView: View {

    private var recipes = [Recipe]()
    
    init() {
        let recipeData = RecipeData()
        recipes = recipeData.getSearchData()
    }
    var body: some View {
        List {
            ForEach(recipes, id: \.self.id){ recipe in
                VStack{
                    RecipeCard(recipe: recipe)
                    Spacer(minLength: 10)
                }
                .background(Color.init(hex: 0xf2f2f2))
            }
            .listRowInsets(EdgeInsets())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
