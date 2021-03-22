package com.plugow.recipeapp.data.api

import com.squareup.moshi.Json

data class RecipeDto(
        val pk: Int,
        val title: String,
        val publisher: String,
        @field:Json(name="featured_image") val featuredImage: String,
        val rating: Int = 0,
        @field:Json(name="source_url") val sourceUrl: String,
        val ingredients: List<String> = emptyList(),
        @field:Json(name="date_added") val dateAdded: String,
        @field:Json(name="date_updated") val dateUpdated: String,
)