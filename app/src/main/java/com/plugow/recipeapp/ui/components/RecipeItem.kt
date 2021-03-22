package com.plugow.recipeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plugow.recipeapp.R
import com.plugow.recipeapp.data.RecipeItem

@Composable
fun RecipeItem(recipeItem: RecipeItem, index: Int, onItemClick: (Int) -> Unit) {
    Card(elevation = 5.dp, shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(8.dp).clickable {
        onItemClick(recipeItem.id)
    }.fillMaxWidth(), contentColor = Color.LightGray) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LoadingImage(url = recipeItem.imageUrl, defaultImage = DEFAULT_IMAGE)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = recipeItem.name)
                Text(text = recipeItem.recommendationsAmount.toString())
            }

        }
    }
}

@Composable
fun RecipeList(recipes: List<RecipeItem>, onItemClick: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(recipes) { index: Int, item: RecipeItem ->
            RecipeItem(
                recipeItem = item,
                index = index,
                onItemClick = onItemClick
            )

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipeItem = RecipeItem(
            1,
            "Test",
            "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            13
        ),
        index = 0,
        onItemClick = { /*TODO*/ })
}