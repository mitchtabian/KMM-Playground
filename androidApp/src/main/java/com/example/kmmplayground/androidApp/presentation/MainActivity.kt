package com.example.kmmplayground.androidApp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmmplayground.shared.Greeting
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kmmplayground.androidApp.components.RecipeList
import com.example.kmmplayground.androidApp.presentation.theme.AppTheme
import com.example.kmmplayground.shared.domain.data.RecipeData
import kotlinx.coroutines.ExperimentalCoroutinesApi

fun greet(): String {
    return Greeting().greeting()
}

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    private val recipeData = RecipeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipes = recipeData.getSearchData()
        setContent {
            AppTheme(
                darkTheme = false,
            ){
                RecipeList(
                    loading = false,
                    recipes = recipes,
                    onChangeScrollPosition = {

                    },
                    page = 1,
                    onTriggerNextPage = {

                    },
                    onNavigateToRecipeDetailScreen = {

                    }
                )
            }
        }
    }
}
