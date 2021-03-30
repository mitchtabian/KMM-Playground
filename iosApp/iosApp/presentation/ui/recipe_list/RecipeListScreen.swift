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
    
    init(
        searchRecipes: SearchRecipes,
        token: String,
        foodCategoryUtil: FoodCategoryUtil
    ) {
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
    static let recipeService = RecipeServiceImpl()
    static let dtoMapper = RecipeDtoMapper()
    static let driverFactory = DriverFactory()
    static let recipeEntityMapper = RecipeEntityMapper()
    static let dateUtil = DateUtil()
    static let recipeDatabase = RecipeDatabaseFactory(driverFactory: driverFactory).createDatabase()
    static let searchRecipes = SearchRecipes(
        recipeService: recipeService,
        dtoMapper: dtoMapper,
        recipeDatabase: recipeDatabase,
        recipeEntityMapper: recipeEntityMapper,
        dateUtil: dateUtil
    )
    static var previews: some View {
        RecipeListScreen(
            searchRecipes: searchRecipes,
            token: ApiTokenProvider().provideToken(),
            foodCategoryUtil: FoodCategoryUtil()
        )
    }
}
