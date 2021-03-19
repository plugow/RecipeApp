package com.plugow.recipeapp.util.api

import com.plugow.recipeapp.data.RecipeId
import com.plugow.recipeapp.data.api.RecipeDto
import com.plugow.recipeapp.data.api.RecipeList
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("search")
    suspend fun getRecipeList(
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeList

    @GET("get")
    suspend fun getRecipe(
        @Query("id") id: RecipeId
    ): RecipeDto
}
