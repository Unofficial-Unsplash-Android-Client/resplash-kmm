package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.image.UserAvatarItem

@Composable
fun ProfileInfoHeader(
    modifier: Modifier = Modifier,
    onDownloadClick: (String) -> Unit,
    onLikeClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(all = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        UserAvatarItem(
            modifier = Modifier.size(size = 40.dp),
            avatarUrl = "https://break.cas.sk/wp-content/uploads/2021/05/Screenshodsdst2.png"
        )
        Text(text = "Elena Salome", modifier = Modifier.padding(start = 8.dp, end = 16.dp))
        Icon(
            modifier = Modifier
                .padding(start = 50.dp)
                .clip(CircleShape)
                .clickable { onLikeClick() },
            painter = painterResource(id = R.drawable.ic_fav),
            contentDescription = ""
        )
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDownloadClick("url") },
            painter = painterResource(id = R.drawable.ic_download),
            contentDescription = ""
        )
    }
}
