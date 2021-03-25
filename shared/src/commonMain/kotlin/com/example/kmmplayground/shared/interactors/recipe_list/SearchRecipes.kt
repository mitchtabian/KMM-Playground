package com.example.kmmplayground.shared.interactors.recipe_list

import com.example.kmmplayground.shared.domain.data.DataState
import com.example.kmmplayground.shared.domain.model.Recipe
import com.example.kmmplayground.shared.domain.util.CommonFlow
import com.example.kmmplayground.shared.domain.util.asCommonFlow
import com.example.kmmplayground.shared.network.RecipeService
import com.example.kmmplayground.shared.network.model.RecipeDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val dtoMapper: RecipeDtoMapper,
) {

    @Throws(Exception::class)
    fun execute(
        token: String,
        page: Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow  {
        try{
            emit(DataState.loading())

            // just to show pagination, api is fast
            delay(1000)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            // Convert: NetworkRecipeEntity -> Recipe -> RecipeCacheEntity
            val recipes = getRecipesFromNetwork(
                token = token,
                page = page,
                query = query,
            )

            // TODO("insert into cache")

            emit(DataState.success(recipes))

        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()

    // WARNING: This will throw exception if there is no network connection
    private suspend fun getRecipesFromNetwork(
        token: String,
        page: Int,
        query: String
    ): List<Recipe> {
        return dtoMapper.toDomainList(
            recipeService.search(
                token = token,
                page = page,
                query = query,
            ).results
        )
    }
}








