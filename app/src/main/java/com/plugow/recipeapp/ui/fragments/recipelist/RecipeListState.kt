package com.plugow.recipeapp.ui.fragments.recipelist

import com.plugow.recipeapp.data.RecipeItem

sealed class RecipeListState {
    object Loading: RecipeListState()
    object Error: RecipeListState()
    class Success(val recipes: List<RecipeItem>): RecipeListState()
}