package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.math.min
import ru.fursa.unsplash.android.base.screen.toPx
import ru.fursa.unsplash.android.ui.kit.text.UserHeaderItem

@Composable
fun PhotoListItem(
    url: String,
    width: Int = 300,
    height: Int = 300,
    username: String,
    fullName: String,
    avatarUrl: String,
    onUserClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        val context = LocalContext.current
        val screenWidth = with(LocalConfiguration.current) { screenWidthDp.dp.value.toPx() }
        val screenHeight = with(LocalConfiguration.current) { screenHeightDp.dp.value.toPx() }

        val scale = min(screenWidth / width, screenHeight / height)

        val resultWidth = (width * scale) / 2
        val resultHeight = (height * scale) / 2

        Spacer(modifier = Modifier.size(16.dp))

        UserHeaderItem(
            username = username,
            avatarUrl = avatarUrl,
            fullName = fullName,
            onUserClick = { username -> onUserClick(username) }
        )

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url)
                .crossfade(durationMillis = 1000)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(resultWidth.dp)
                .height(resultHeight.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}
