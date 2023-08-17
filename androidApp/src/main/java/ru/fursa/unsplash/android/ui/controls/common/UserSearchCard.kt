package ru.fursa.unsplash.android.ui.controls.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.ui.controls.UserAvatarItem

@Composable
fun UserSearchCard(
    profileImageUrl: String,
    username: String,
    instagramAccount: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            UserAvatarItem(modifier = Modifier.size(45.dp), url = profileImageUrl)
            Spacer(modifier = Modifier.size(16.dp))
            Column() {
                Text(
                    text = username, style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = instagramAccount)
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserSearchCard() {
    Box(modifier = Modifier.background(Color.White)) {
        UserSearchCard(
            username = "Илья Косарев",
            instagramAccount = "@kosarev_photographer",
            profileImageUrl = "https://hips.hearstapps.com/hmg-prod/images/beautiful-smooth-haired-red-cat-lies-on-the-sofa-royalty-free-image-1678488026.jpg?crop=0.88847xw:1xh;center,top&resize=1200:*"
        )
    }
}