//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class RecipeListViewModel: ObservableObject{
    
    // Dependencies
    let recipeService: RecipeServiceImpl
    let dtoMapper = RecipeDtoMapper()
    let searchRecipes: SearchRecipes
    private let token = "9c8b06d329136da358c2d00e76946b0111ce2c48"
    
    // Variables
    private let recipeData = RecipeData()
    var recipes = [Recipe]()
    
    private let foodCategoryUtil = FoodCategoryUtil()
    var categories = [FoodCategory]()
    
    @Published var query = ""
    
    @Published var selectedCategory: FoodCategory? = nil
    
    @Published var loading = false
    
    @Published var page = 1
    
    init(recipeService: RecipeServiceImpl){
        self.recipeService = recipeService
        self.searchRecipes = SearchRecipes(recipeService: self.recipeService, dtoMapper: self.dtoMapper)
        categories = foodCategoryUtil.getAllFoodCategories()
        onTriggerEvent(stateEvent: RecipeListEvent.NewSearchEvent())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvent){
        switch stateEvent{
        case RecipeListEvent.NewSearchEvent(): newSearch()
        //case RecipeListEvent.NextPageEvent(): TODO("pagination")
        //case RecipeListEvent.RestoreStateEvent(): TODO("Probably not needed on iOS")
        default:
            doNothing()
        }
    }
    
    func doNothing(){
        
    }
    
    func resetSearchState(){
        recipes = []
        page = 1
        if(selectedCategory?.value != query){
            selectedCategory = nil
        }
    }
    
    func onSelectedCategoryChanged(category: String){
        let newCategory = foodCategoryUtil.getFoodCategory(value: category)
        selectedCategory = newCategory
        setQuery(query: category)
    }
    
    func setQuery(query: String){
        self.query = query
    }
    
    func newSearch() {
        resetSearchState()
        loading = true
        searchRecipes.execute(token: token, page: Int32(page), query: query, completionHandler: { flow, error in
            if error != nil {
                print("ERROR: newSearch: \(error)")
            }else{
                flow?.watch { dataState in
                    if dataState != nil {
//                        print(dataState)
                        guard let data = dataState?.data else {
                            guard let _error = dataState?.error else{
                                guard let _loading = dataState?.loading else{
                                    print("ERROR: newSearch: The use case was successful but there is no data.")
                                    return
                                }
                                // ignore loading
                                return
                            }
                            print("ERROR: newSearch: \(_error)")
                            return
                        }
                        self.recipes = data as! [Recipe]
                        print(self.recipes)
                        
//                        print(data)
//                        if(dataState?.error != nil){
//                            print("ERROR: newSearch: \(dataState?.error)")
//                        }
//                        if(dataState?.data != nil){
//                            print("recipes: ...")
//                            self.recipes = dataState?.data as! [Recipe]
//                        }
                    }else{
                        print("ERROR: newSearch: DataState is nil")
                    }
                }
            }
            self.loading = false
        })
    }
}
