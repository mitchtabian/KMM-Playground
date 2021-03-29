package com.example.kmmplayground.androidApp.di

import com.example.kmmplayground.cache.RecipeDatabase
import com.example.kmmplayground.shared.cache.model.RecipeEntityMapper
import com.example.kmmplayground.shared.domain.util.DateUtil
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
        entityMapper: RecipeEntityMapper,
        recipeDatabase: RecipeDatabase,
        dateUtil: DateUtil
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            dtoMapper = dtoMapper,
            recipeEntityMapper = entityMapper,
            recipeDatabase = recipeDatabase,
            dateUtil = dateUtil
        )
    }
}









