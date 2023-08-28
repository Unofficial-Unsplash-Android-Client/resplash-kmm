package ru.fursa.unsplash.android.ui.kit.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.fursa.unsplash.android.R

@Composable
fun UserAvatarItem(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    avatarUrl: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarUrl)
            .crossfade(durationMillis = 1000)
            .build(),
        contentDescription = "user avatar",
        contentScale = contentScale,
        modifier = modifier
            .clip(CircleShape)
    )
}

@Composable
fun DefaultUserAvatar(
    modifier: Modifier = Modifier,
) {
    Image(
        contentScale = ContentScale.Inside,
        modifier = modifier
            .clip(CircleShape)
            .size(45.dp)
            .background(Color.LightGray)
            .alpha(0.5f)
            .padding(top = 3.dp),
        painter = painterResource(id = R.drawable.round_person_24),
        contentDescription = "default avatar"
    )
}

@Preview
@Composable
fun UserAvatarPreview() {
    Column(modifier = Modifier.wrapContentSize()) {
        UserAvatarItem(
            avatarUrl = "https://shorturl.at/gjzDI"
        )
    }
}
