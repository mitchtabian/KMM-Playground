package com.example.kmmplayground.shared.interactors.recipe

import com.example.kmmplayground.cache.RecipeDatabase
import com.example.kmmplayground.shared.datasource.cache.model.RecipeEntityMapper
import com.example.kmmplayground.shared.domain.data.DataState
import com.example.kmmplayground.shared.domain.model.Recipe
import com.example.kmmplayground.shared.domain.util.DateUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
  private val recipeDatabase: RecipeDatabase,
  private val recipeEntityMapper: RecipeEntityMapper,
  private val dateUtil: DateUtil,
){

  fun execute(
    recipeId: Int,
  ): Flow<DataState<Recipe>> = flow {
    try {
      emit(DataState.loading())

      // just to show loading, cache is fast
      delay(1000)

      val recipe = getRecipeFromCache(recipeId = recipeId)

      emit(DataState.success(recipe))

    }catch (e: Exception){
      emit(DataState.error<Recipe>(e.message?: "Unknown Error"))
    }
  }

  private fun getRecipeFromCache(recipeId: Int): Recipe {
    return recipeDatabase.recipeDbQueries.getRecipeById(recipeId.toLong()).executeAsOne().let { entity ->
      Recipe(
        id = entity.id.toInt(),
        title = entity.title,
        publisher = entity.publisher,
        featuredImage = entity.featured_image,
        rating = entity.rating.toInt(),
        sourceUrl = entity.source_url,
        ingredients = recipeEntityMapper.convertIngredientsToList(entity.ingredients),
        dateAdded = dateUtil.toLocalDate(entity.date_added),
        dateUpdated = dateUtil.toLocalDate(entity.date_updated)
      )
    }
  }

}