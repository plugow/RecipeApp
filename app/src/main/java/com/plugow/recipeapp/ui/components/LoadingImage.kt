package com.plugow.recipeapp.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.plugow.recipeapp.R

const val DEFAULT_IMAGE = R.drawable.empty_plate

@Composable
fun LoadingImage(url: String, @DrawableRes defaultImage: Int, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Fit){
    var bitmapState by remember { mutableStateOf<Bitmap?>(null) }

    if (bitmapState == null) {
        Image(modifier = modifier, painter = painterResource(id = defaultImage), contentDescription = "", contentScale = contentScale)
    } else {
        Image(modifier = modifier, bitmap = bitmapState!!.asImageBitmap(), contentDescription ="recipe image", contentScale = contentScale)
    }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}