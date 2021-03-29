import SwiftUI
import shared

@available(iOS 14.0, *)
struct ContentView: View {
    
    init(){
        let largeTitleFontAttrs = [ NSAttributedString.Key.font: UIFont(name: "avenir", size: 24)
        ]
        let smallTitleFontAttrs = [ NSAttributedString.Key.font: UIFont(name: "avenir", size: 20)
        ]
        
        
        UINavigationBar.appearance().titleTextAttributes = smallTitleFontAttrs
        UINavigationBar.appearance().largeTitleTextAttributes = largeTitleFontAttrs
        
        let container = DIContainer.shared
        
        container.register(name: "auth_token", component: "Token 9c8b06d329136da358c2d00e76946b0111ce2c48")
        container.register(type: FoodCategoryUtil.self, component: FoodCategoryUtil())
        
        let recipeService = RecipeServiceImpl()
        let dtoMapper = RecipeDtoMapper()
        let driverFactory = DriverFactory()
        let recipeEntityMapper = RecipeEntityMapper()
        let dateUtil = DateUtil()
        let recipeDatabase = RecipeDatabaseFactory(driverFactory: driverFactory).createDatabase()
        let searchRecipes = SearchRecipes(
            recipeService: recipeService,
            dtoMapper: dtoMapper,
            recipeDatabase: recipeDatabase,
            recipeEntityMapper: recipeEntityMapper,
            dateUtil: dateUtil
        )
        container.register(type: SearchRecipes.self, component: searchRecipes)
        
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
