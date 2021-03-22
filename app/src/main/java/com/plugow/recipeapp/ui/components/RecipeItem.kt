package com.plugow.recipeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage
import com.plugow.recipeapp.data.RecipeItem

@Composable
fun RecipeItem(recipeItem: RecipeItem, onItemClick: (Int) -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick(recipeItem.id)
            }
            .fillMaxWidth(),
        contentColor = Color.LightGray
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                data = recipeItem.imageUrl,
                contentDescription = "recipe image",
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Image(painter = painterResource(id = DEFAULT_IMAGE), contentDescription = "")
                },
                fadeIn = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = recipeItem.name)
                Text(text = recipeItem.recommendationsAmount.toString())
            }

        }
    }
}

@Composable
fun RecipeList(recipes: List<RecipeItem>, onItemClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipes) { item: RecipeItem ->
            RecipeItem(
                recipeItem = item,
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
        onItemClick = { /*TODO*/ })
}