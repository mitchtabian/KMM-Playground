package com.example.kmmplayground.androidApp.di

import com.example.kmmplayground.shared.interactors.recipe_list.SearchRecipes
import com.example.kmmplayground.shared.network.RecipeService
import com.example.kmmplayground.shared.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        dtoMapper: RecipeDtoMapper,
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            dtoMapper = dtoMapper,
        )
    }
}









