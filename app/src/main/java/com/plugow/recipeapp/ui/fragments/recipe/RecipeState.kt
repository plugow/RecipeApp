package com.plugow.recipeapp.ui.fragments.recipe

import com.plugow.recipeapp.data.api.RecipeDto

sealed class RecipeState {
    object Loading: RecipeState()
    class Error(cause: String): RecipeState()
    class Success(recipe: RecipeDto): RecipeState()
}