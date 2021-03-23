//
//  RecipeListScreen.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    private var recipes = [Recipe]()
    
    init() {
        let recipeData = RecipeData()
        recipes = recipeData.getSearchData()
    }
    
    var body: some View {
        NavigationView{
            List {
                ForEach(recipes, id: \.self.id){ recipe in
                    ZStack{
                        VStack{
                            RecipeCard(recipe: recipe)
                            Spacer(minLength: 10)
                        }
                        NavigationLink(
                            destination: RecipeScreen(recipe: recipe)
                        ){
                            // workaround for hiding arrows
                            EmptyView()
                        }.hidden().frame(width: 0)
                    }
                }
                .listRowInsets(EdgeInsets())
                .listRowBackground(Color.init(hex: 0xf2f2f2))
            }
            .navigationBarHidden(true)
        }
        
    }
}

struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen()
    }
}
