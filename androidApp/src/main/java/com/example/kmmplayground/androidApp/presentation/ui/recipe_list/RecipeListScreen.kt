package com.example.kmmplayground.androidApp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.kmmplayground.androidApp.presentation.components.RecipeList
import com.example.kmmplayground.androidApp.presentation.components.SearchAppBar
import com.example.kmmplayground.androidApp.presentation.theme.AppTheme
import com.example.kmmplayground.shared.presentation.ui.recipe_list.RecipeListEvent
import com.example.kmmplayground.shared.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
  isDarkTheme: Boolean,
  onToggleTheme: () -> Unit,
  onNavigateToRecipeDetailScreen: (String) -> Unit,
  viewModel: RecipeListViewModel,
) {
  Log.d(TAG, "RecipeListScreen: ${viewModel}")

  val recipes = viewModel.recipes.value

  val query = viewModel.query.value

  val selectedCategory = viewModel.selectedCategory.value

  val loading = viewModel.loading.value

  val page = viewModel.page.value

  val foodCategories = viewModel.foodCategories.value

  val scaffoldState = rememberScaffoldState()

  val dialogQueue = viewModel.dialogQueue

  AppTheme(
    displayProgressBar = loading,
    scaffoldState = scaffoldState,
    darkTheme = isDarkTheme,
    dialogQueue = dialogQueue.queue.value,
  ) {
    Scaffold(
      topBar = {
        SearchAppBar(
          query = query,
          onQueryChanged = viewModel::onQueryChanged,
          onExecuteSearch = {
            viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
          },
          categories = foodCategories,
          selectedCategory = selectedCategory,
          onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
          onToggleTheme = { onToggleTheme() }
        )
      },
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) {
      RecipeList(
        loading = loading,
        recipes = recipes,
        onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
        page = page,
        onTriggerNextPage = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent) },
        onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
      )
    }
  }
}