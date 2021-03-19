package com.plugow.recipeapp.data.api

import com.squareup.moshi.Json

data class RecipeList(
    val count: Int,
    @Json(name="results") val recipes:List<RecipeDto>
)