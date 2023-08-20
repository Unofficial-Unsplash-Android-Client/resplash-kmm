package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.fursa.unsplash.android.ui.kit.image.UserAvatarItem
import ru.fursa.unsplash.data.api.models.photo.Photo

@Composable
fun UserSearchCard(
    profileImageUrl: String,
    username: String,
    instagramAccount: String,
    photos: List<Photo>
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            UserAvatarItem(modifier = Modifier.size(50.dp), avatarUrl = profileImageUrl)
            Spacer(modifier = Modifier.size(16.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = username, style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = when {
                        instagramAccount.isEmpty() -> ""
                        else -> "@$instagramAccount"
                    }
                )

            }

        }

        LazyRow(
            content = {
                items(photos.size) { index ->
                    val photo = photos[index]

                    Spacer(modifier = Modifier.size(8.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(photo.urls.regular)
                            .crossfade(durationMillis = 1000)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(120.dp)
                            .height(90.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                }
            }, contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewUserSearchCard() {
    Box(modifier = Modifier.background(Color.White)) {
        UserSearchCard(
            username = "Илья Косарев",
            instagramAccount = "@kosarev_photographer",
            profileImageUrl = "https://hips.hearstapps.com/hmg-prod/images/beautiful-smooth-haired-red-cat-lies-on-the-sofa-royalty-free-image-1678488026.jpg?crop=0.88847xw:1xh;center,top&resize=1200:*",
            photos = emptyList()
        )
    }
}