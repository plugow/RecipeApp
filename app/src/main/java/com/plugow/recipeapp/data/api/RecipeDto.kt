package com.plugow.recipeapp.data.api

import com.squareup.moshi.Json

data class RecipeDto(
        val pk: Int,
        val title: String,
        val publisher: String,
        @Json(name="featured_image") val featuredImage: String,
        val rating: Int = 0,
        @Json(name="source_url") val sourceUrl: String,
        val ingredients: List<String> = emptyList(),
        @Json(name="date_added") val dateAdded: String,
        @Json(name="date_updated") val dateUpdated: String,
)