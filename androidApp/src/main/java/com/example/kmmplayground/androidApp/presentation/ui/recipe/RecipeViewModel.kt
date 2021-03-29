package com.example.kmmplayground.androidApp.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmplayground.shared.domain.data.RecipeData
import com.example.kmmplayground.shared.domain.model.Recipe
import com.example.kmmplayground.shared.interactors.recipe.GetRecipe
import com.example.kmmplayground.shared.presentation.ui.recipe.RecipeEvent
import com.example.kmmplayground.shared.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RECIPE = "recipe.state.recipe.key"

@ExperimentalCoroutinesApi
@HiltViewModel
class RecipeViewModel
@Inject
constructor(
    private val state: SavedStateHandle,
    private val getRecipe: GetRecipe,
): ViewModel(){

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    val onLoad: MutableState<Boolean> = mutableStateOf(false)

    init {
        // restore if process dies
        state.get<Int>(STATE_KEY_RECIPE)?.let{ recipeId ->
            onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is RecipeEvent.GetRecipeEvent -> {
                        if(recipe.value == null){
                            getRecipe(event.id)
                        }
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private fun getRecipe(id: Int){
        getRecipe.execute(id).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { data ->
                recipe.value = data
                state.set(STATE_KEY_RECIPE, data.id)
            }

            dataState.error?.let { error ->
                Log.e(TAG, "getRecipe: ${error}")
            }
        }.launchIn(viewModelScope)
    }
}
