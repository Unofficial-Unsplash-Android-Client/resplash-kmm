package ru.fursa.unsplash.android.ui.kit.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.ui.kit.image.UserAvatarItem

@Composable
fun UserHeaderItem(
    username: String,
    avatarUrl: String,
    fullName: String,
    onUserClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 6.dp)
            .clickable { onUserClick.invoke(fullName) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatarItem(modifier = Modifier.size(40.dp), avatarUrl = avatarUrl)
        Text(text = username, modifier = Modifier.padding(start = 8.dp))
    }
}