package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.clickable
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
import ru.fursa.unsplash.data.ui.models.PhotoModel

@Composable
fun PhotoListItem(
    photoModel: PhotoModel,
    onUserClick: (String) -> Unit,
    onViewPhoto: (PhotoModel) -> Unit,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        val context = LocalContext.current
        val screenWidth = with(LocalConfiguration.current) { screenWidthDp.dp.value.toPx() }
        val screenHeight = with(LocalConfiguration.current) { screenHeightDp.dp.value.toPx() }

        val scale = min(screenWidth / photoModel.width, screenHeight / photoModel.height)

        val resultWidth = (photoModel.width * scale) / 2
        val resultHeight = (photoModel.height * scale) / 2

        Spacer(modifier = Modifier.size(16.dp))

        UserHeaderItem(
            username = photoModel.username,
            avatarUrl = photoModel.profileImage,
            fullName = photoModel.fullName,
            onUserClick = { username -> onUserClick(username) }
        )

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(photoModel.photoUrl)
                .crossfade(durationMillis = 1000)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(resultWidth.dp)
                .height(resultHeight.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable { onViewPhoto(photoModel) }
        )
    }
}
