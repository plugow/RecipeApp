package com.plugow.recipeapp.util.api.repo

import com.plugow.recipeapp.data.Either
import com.plugow.recipeapp.data.RecipeId
import com.plugow.recipeapp.data.api.RecipeDto
import com.plugow.recipeapp.data.map
import com.plugow.recipeapp.util.api.RecipeApi
import com.plugow.recipeapp.util.errorhandling.Failure
import com.plugow.recipeapp.util.errorhandling.handleRequest
import javax.inject.Inject

class RecipeRepoImpl @Inject constructor(private val api: RecipeApi): RecipeRepo {
    override suspend fun getRecipe(recipeId: RecipeId): Either<Failure, RecipeDto> {
        return handleRequest {
            api.getRecipe(recipeId)
        }
    }

    override suspend fun getRecipeList(page: Int, query: String): Either<Failure, List<RecipeDto>> {
        return handleRequest {
            api.getRecipeList(page, query)
        }.map {
            it.recipes
        }
    }
}