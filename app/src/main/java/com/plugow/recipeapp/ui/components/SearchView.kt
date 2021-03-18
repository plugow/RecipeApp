package com.plugow.recipeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchView(text: String, modifier: Modifier = Modifier, onTextChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        label = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, "Search")
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
    )

}


@Preview(showSystemUi = true)
@Composable
fun SearchPreview() {
    SearchView("Dupa", Modifier.fillMaxWidth(),{

    })
}