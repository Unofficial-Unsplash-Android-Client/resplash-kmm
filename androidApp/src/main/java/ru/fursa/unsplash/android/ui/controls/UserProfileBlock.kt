package ru.fursa.unsplash.android.ui.controls

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserProfileBlock(avatarUrl: String, fullName: String) {
    Row(
        modifier = Modifier.padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatarItem(modifier = Modifier.padding(end = 16.dp), url = avatarUrl)
        Text(text = fullName)
    }
}

@Composable
@Preview
fun UserProfileBlockPreview() {
    Column(modifier = Modifier.wrapContentSize()) {
        UserProfileBlock(
            avatarUrl = "https://hips.hearstapps.com/hmg-prod/images/beautiful-smooth-haired-red-cat-lies-on-the-sofa-royalty-free-image-1678488026.jpg?crop=0.88847xw:1xh;center,top&resize=1200:*",
            fullName = "Ilya Fursa"
        )
    }
}