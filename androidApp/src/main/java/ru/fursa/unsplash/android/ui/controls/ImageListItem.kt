package ru.fursa.unsplash.android.ui.controls

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageListItem(url: String) {
    Box(modifier = Modifier.wrapContentSize().padding(6.dp).clip(RoundedCornerShape(6.dp))) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(false)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(6.dp)))
    }
}

@Composable
@Preview(showBackground = true)
fun ShowImageListItem() {
    Column(modifier = Modifier.fillMaxSize()) {
        ImageListItem(url = "https://i.redd.it/nemfmtfu30iz.jpg")
    }
}