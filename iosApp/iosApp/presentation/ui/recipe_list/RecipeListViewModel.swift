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
        recipeService.search(token: "9c8b06d329136da358c2d00e76946b0111ce2c48", page: 1, query: self.query, completionHandler: { recipeSearchResponse, error in
            if error != nil {
                print("ERROR: newSearch: \(error)")
            }else{
                // Shouldn't have to check for nil on `recipeSearchResponse`... Must be an ios thing?
                if ((recipeSearchResponse != nil) && (recipeSearchResponse?.results) != nil) {
                    let dtoMapper = RecipeDtoMapper()
                    self.recipes = dtoMapper.toDomainList(initial: recipeSearchResponse!.results)
                }
            }
            self.loading = false
        })
    }
}
