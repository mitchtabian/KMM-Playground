package com.example.kmmplayground.shared.presentation.ui.recipe_list

sealed class RecipeListEvent {

    object NewSearchEvent : RecipeListEvent()

    object NextPageEvent : RecipeListEvent()

    // restore after process death (Android Only)
    object RestoreStateEvent: RecipeListEvent()
}