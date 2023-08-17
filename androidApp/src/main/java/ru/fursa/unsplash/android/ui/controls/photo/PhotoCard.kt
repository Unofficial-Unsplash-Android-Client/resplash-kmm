package ru.fursa.unsplash.android.ui.controls.photo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.fursa.unsplash.android.ui.controls.UserAvatarItem

@Composable
fun PhotoCard(url: String, username: String, profileImage: String, onAuthorClick: () -> Unit) {
    Column(modifier = Modifier.padding(6.dp)) {
        Row(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .clickable {
                    onAuthorClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatarItem(modifier = Modifier.size(40.dp), url = profileImage)
            Text(text = username, modifier = Modifier.padding(start = 8.dp))
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(durationMillis = 1000)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}