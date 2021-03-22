package com.plugow.recipeapp.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plugow.recipeapp.ui.fragments.recipe.RecipeDetailsScreen
import com.plugow.recipeapp.ui.fragments.recipelist.RecipeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipelist") {
        composable("recipelist") {
            RecipeListScreen(navController = navController, hiltNavGraphViewModel())
        }
        composable(
            "recipe/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            RecipeDetailsScreen(navController = navController, backStackEntry.arguments?.getInt("recipeId"), hiltNavGraphViewModel())
        }
    }
}