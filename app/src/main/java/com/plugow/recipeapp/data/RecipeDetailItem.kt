package com.plugow.recipeapp.data

data class RecipeDetailItem(
    val image: String,
    val name: String,
    val rating: String,
    val updatedAt: String,
    val ingredients: List<String>
)
