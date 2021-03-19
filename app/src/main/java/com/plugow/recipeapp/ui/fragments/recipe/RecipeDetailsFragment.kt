package com.plugow.recipeapp.ui.fragments.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.plugow.recipeapp.ui.components.DEFAULT_IMAGE
import com.plugow.recipeapp.ui.components.LoadingImage

@Composable
fun RecipeDetailsScreen(navController: NavController, recipeId: Int?, recipeViewModel: RecipeDetailsViewModel = viewModel()) {
    LaunchedEffect(recipeId) {
        recipeViewModel.getRecipe(recipeId)
    }
    val recipeState = recipeViewModel.recipeState.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        LoadingImage(
            url = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            defaultImage = DEFAULT_IMAGE,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Easy Crockpot Pulled Pork", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "13")
        }
        Text(text = "Updated November 11", fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        Text(text = "", modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    }
}
