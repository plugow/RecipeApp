package com.plugow.recipeapp.ui.fragments.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.glide.GlideImage
import com.plugow.recipeapp.data.RecipeDetailItem
import com.plugow.recipeapp.ui.components.DEFAULT_IMAGE

@Composable
fun RecipeDetailsScreen(navController: NavController, recipeId: Int?, recipeViewModel: RecipeDetailsViewModel) {
    LaunchedEffect(recipeId) {
        recipeViewModel.getRecipe(recipeId)
    }
    recipeViewModel.recipeState.observeAsState().value?.let {
        RenderScreen(state = it)
    }

}

@Composable
fun Recipe(recipe: RecipeDetailItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        GlideImage(
            data = recipe.image,
            contentDescription="recipe image",
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            error = {
                Image(painter = painterResource(id=DEFAULT_IMAGE), contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillWidth,
            fadeIn = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = recipe.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = recipe.rating)
        }
        Text(text = recipe.updatedAt, fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        for (ingredient in recipe.ingredients) {
            Text(text = ingredient, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
    }
}

@Composable
fun RenderScreen(state: RecipeState) {
    when(state) {
        is RecipeState.Error -> TODO()
        RecipeState.Loading -> LoadingScreen()
        is RecipeState.Success -> Recipe(recipe = state.recipe)
    }
}