package com.plugow.recipeapp.ui.fragments.recipelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.plugow.recipeapp.data.CategoryItem
import com.plugow.recipeapp.ui.components.RecipeList
import com.plugow.recipeapp.ui.components.SearchView

@Composable
fun RecipeListScreen(navController: NavController, viewModel: RecipeListViewModel) {
    val recipeListState: State<RecipeListState?> = viewModel.recipes.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            SearchView(text = "", onTextChange = { /*TODO*/ })
            Icon(Icons.Default.MoreVert, "Change theme",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
            )
        }
        CategoryList(categories = listOf(CategoryItem("jeden", true), CategoryItem("dwa", false)), onItemClick = { /*TODO*/ })
        Spacer(modifier = Modifier.padding(16.dp))
        if (recipeListState.value is RecipeListState.Success){
            RecipeList(
                recipes = (recipeListState.value as RecipeListState.Success).recipes, onItemClick = {
                    navController.navigate("recipe/$it")
                })
        }

    }


}

@Composable
fun CategoryList(categories: List<CategoryItem>, onItemClick: (Int) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            CategoryItem(category, index, onItemClick)
        }
    }
}

@Composable
fun CategoryItem(category: CategoryItem,index: Int, onItemClick: (Int) -> Unit) {
    Button(onClick = {
        onItemClick(index)
    },
        enabled = !category.isSelected
    ) {
        Text(text = category.name)
    }
}



@Preview(showSystemUi = true)
@Composable
fun RecipeListPreview() {
//    val navController = rememberNavController()
//    RecipeListScreen(navController)
}