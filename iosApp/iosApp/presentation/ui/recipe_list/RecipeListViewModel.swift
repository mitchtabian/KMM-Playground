//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by Mitch Tabian on 2021-03-24.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class RecipeListViewModel: ObservableObject{
    
    private let recipeData = RecipeData()
    var recipes = [Recipe]()
    
    private let foodCategoryUtil = FoodCategoryUtil()
    var categories = [FoodCategory]()
    
    @Published var query = ""
    
    @Published var selectedCategory: FoodCategory? = nil
    
    @Published var loading = false
    
    @Published var page = 1
    
    init(){
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
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.loading = false
            self.recipes = self.recipeData.getSearchData()
        }
    }
}
