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
import com.example.kmmplayground.shared.interactors.recipe_list.SearchRecipes
import com.example.kmmplayground.shared.navigation.Screen
import com.example.kmmplayground.shared.network.RecipeServiceImpl
import com.example.kmmplayground.shared.network.model.RecipeDtoMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val searchRecipes = SearchRecipes(
//            recipeService = RecipeServiceImpl(),
//            dtoMapper = RecipeDtoMapper()
//        )
//            searchRecipes.execute(
//                token = "9c8b06d329136da358c2d00e76946b0111ce2c48",
//                page = 1,
//                query = ""
//            ).onEach { dataState ->
//                println(dataState)
//            }.launchIn(CoroutineScope(IO))

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
