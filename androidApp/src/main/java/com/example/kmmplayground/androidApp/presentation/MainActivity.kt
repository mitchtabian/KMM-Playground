package com.example.kmmplayground.androidApp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.kmmplayground.androidApp.presentation.ui.recipe.RecipeDetailScreen
import com.example.kmmplayground.androidApp.presentation.ui.recipe.RecipeViewModel
import com.example.kmmplayground.androidApp.presentation.ui.recipe_list.RecipeListScreen
import com.example.kmmplayground.androidApp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.kmmplayground.shared.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
                    RecipeListScreen(
                        isDarkTheme = false,
                        onToggleTheme = {},
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        viewModel = viewModel,
                    )
                }
                composable(
                    route = Screen.RecipeDetail.route + "/{recipeId}",
                    arguments = listOf(navArgument("recipeId") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = false,
                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
