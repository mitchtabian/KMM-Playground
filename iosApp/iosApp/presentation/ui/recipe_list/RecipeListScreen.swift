//
//  RecipeListScreen.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 14.0, *)
struct RecipeListScreen: View {
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(){
        let searchRecipes = DIContainer.shared.resolve(type: SearchRecipes.self)
        let token = DIContainer.shared.resolve<String>(name: "auth_token")
        let foodCategoryUtil = DIContainer.shared.resolve(type: FoodCategoryUtil.self)
        viewModel = RecipeListViewModel(
            searchRecipes: searchRecipes,
            token: token,
            foodCategoryUtil: foodCategoryUtil
        )
    }
    
    var body: some View {
        NavigationView{
            ZStack{
                VStack{
                    SearchAppBar(viewModel: viewModel)
                    List{
                        ForEach(viewModel.recipes, id: \.self.id){ recipe in
                            ZStack{
                                VStack{
                                    RecipeCard(recipe: recipe)
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(recipe: recipe){
                                                viewModel.onTriggerEvent(stateEvent: RecipeListEvent.NextPageEvent())
                                            }
                                        })
                                }
                                NavigationLink(
                                    destination: RecipeScreen(recipe: recipe)
                                ){
                                    // workaround for hiding arrows
                                    EmptyView()
                                }.hidden().frame(width: 0)
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
                    .background(Color.init(hex: 0xf2f2f2))
                }
                if viewModel.loading {
                    ProgressView("Searching recipes...")
                }
            }
            .navigationBarHidden(true)
            
        }
    }
}

@available(iOS 14.0, *)
struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen()
    }
}
