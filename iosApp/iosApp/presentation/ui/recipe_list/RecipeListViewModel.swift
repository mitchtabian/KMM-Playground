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
    
    // The current query
    @Published var query = ""
    
    // Selected category chip in SearchBar
    @Published var selectedCategory: FoodCategory? = nil
    
    // Show/hide progress bar
    @Published var loading = false
    
    // Page number for pagination
    @Published var page = 1
    
    // track the recipe at the bottom of the list so we know when to trigger pagination
    private var bottomRecipe: Recipe? = nil
    
    // Is a query currently in progress? This will prevent duplicate queries.
    private var isQueryInProgress = false
    
    init(recipeService: RecipeServiceImpl){
        self.recipeService = recipeService
        self.searchRecipes = SearchRecipes(recipeService: self.recipeService, dtoMapper: self.dtoMapper)
        categories = foodCategoryUtil.getAllFoodCategories()
        onTriggerEvent(stateEvent: RecipeListEvent.NewSearchEvent())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvent){
        switch stateEvent{
        case RecipeListEvent.NewSearchEvent(): newSearch()
        case RecipeListEvent.NextPageEvent(): nextPage()
        //case RecipeListEvent.RestoreStateEvent(): TODO("Probably not needed on iOS")
        default:
            doNothing()
        }
    }
    
    func doNothing(){
        
    }
    
    private func resetSearchState(){
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
    
    private func updateBottomRecipe(recipe: Recipe){
        bottomRecipe = recipe
    }
    
    private func incrementPage(){
        page = page + 1
    }
    
    private func appendRecipes(recipes: [Recipe]){
        self.recipes.append(contentsOf: recipes)
        self.updateBottomRecipe(recipe: self.recipes[self.recipes.count - 1])
    }
    
    // TODO("Figure out how I'm going to handle the errors")
    private func handleError(_ error: String){
        // print(error)
    }
    
    private func newSearch() {
        resetSearchState()
        do{
            try searchRecipes.execute(token: token, page: Int32(page), query: query).watch(block: {dataState in
                if dataState != nil {
                    let _data = dataState?.data
                    let _error = dataState?.error
                    let _loading = dataState?.loading ?? false
                    
                    self.loading = _loading
                    if(_data != nil){
                        self.appendRecipes(recipes: _data as! [Recipe])
                    }
                    if(_error != nil){
                        self.handleError("ERROR: newSearch: \(_error)")
                    }
                }else{
                    self.handleError("ERROR: newSearch: DataState is nil")
                }
            })
        }catch{
            self.handleError("ERROR: newSearch: \(error)")
        }
    }
    
    func shouldQueryNextPage(recipe: Recipe) -> Bool {
        // check if looking at the bottom recipe
        // if lookingAtBottom -> proceed
        // if PAGE_SIZE * page <= recipes.length
        // if !queryInProgress
        // else -> do nothing
        if(recipe.id == self.bottomRecipe?.id){
            if(Constants.RECIPE_PAGINATION_PAGE_SIZE * page <= recipes.count){
                if(!isQueryInProgress){
                    return true
                }
            }
        }
        return false
    }
    
    private func nextPage(){
        incrementPage()
        print("NEXT PAGE \(page)")
        if(page > 1){
            do{
                try searchRecipes.execute(token: token, page: Int32(page), query: query).watch(block: {dataState in
                    if dataState != nil {
                        let _data = dataState?.data
                        let _error = dataState?.error
                        let _loading = dataState?.loading ?? false
                        
                        self.loading = _loading
                        if(_data != nil){
                            self.appendRecipes(recipes: _data as! [Recipe])
                        }
                        if(_error != nil){
                            self.handleError("ERROR: newSearch: \(_error)")
                        }
                    }else{
                        self.handleError("ERROR: newSearch: DataState is nil")
                    }
                })
            }catch{
                self.handleError("ERROR: newSearch: \(error)")
            }
        }
    }
}


