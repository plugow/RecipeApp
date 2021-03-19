package com.plugow.recipeapp.util.api.repo

import com.plugow.recipeapp.data.Either
import com.plugow.recipeapp.data.RecipeId
import com.plugow.recipeapp.data.api.RecipeDto
import com.plugow.recipeapp.util.errorhandling.Failure

interface RecipeRepo {
    suspend fun getRecipe(recipeId: RecipeId): Either<Failure, RecipeDto>
    suspend fun getRecipeList(page:Int, query: String): Either<Failure, List<RecipeDto>>
}