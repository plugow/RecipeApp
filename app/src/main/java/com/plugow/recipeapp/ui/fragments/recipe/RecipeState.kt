package com.plugow.recipeapp.ui.fragments.recipe

import com.plugow.recipeapp.data.RecipeDetailItem

sealed class RecipeState {
    object Loading: RecipeState()
    class Error(val cause: String): RecipeState()
    class Success(val recipe: RecipeDetailItem): RecipeState()
}