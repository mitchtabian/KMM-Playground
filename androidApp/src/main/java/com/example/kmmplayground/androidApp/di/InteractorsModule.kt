package com.example.kmmplayground.androidApp.di

import com.example.kmmplayground.cache.RecipeDatabase
import com.example.kmmplayground.shared.datasource.cache.model.RecipeEntityMapper
import com.example.kmmplayground.shared.domain.util.DateUtil
import com.example.kmmplayground.shared.interactors.recipe.GetRecipe
import com.example.kmmplayground.shared.interactors.recipe_list.SearchRecipes
import com.example.kmmplayground.shared.datasource.network.RecipeService
import com.example.kmmplayground.shared.datasource.network.model.RecipeDtoMapper
import com.example.kmmplayground.shared.interactors.recipe_list.RestoreRecipes
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

    @Singleton
    @Provides
    fun providerestoreRecipes(
        entityMapper: RecipeEntityMapper,
        recipeDatabase: RecipeDatabase,
        dateUtil: DateUtil
    ): RestoreRecipes {
        return RestoreRecipes(
            entityMapper = entityMapper,
            recipeDatabase = recipeDatabase,
            dateUtil = dateUtil
        )
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeDatabase: RecipeDatabase,
        entityMapper: RecipeEntityMapper,
        dateUtil: DateUtil
    ): GetRecipe {
        return GetRecipe(
            recipeDatabase = recipeDatabase,
            recipeEntityMapper = entityMapper,
            dateUtil = dateUtil
        )
    }
}









