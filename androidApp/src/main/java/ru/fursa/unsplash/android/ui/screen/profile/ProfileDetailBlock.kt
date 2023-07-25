package ru.fursa.unsplash.android.ui.screen.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_TYPE_CAR
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.controls.UserAvatarItem
import ru.fursa.unsplash.android.ui.tabs.TabScreen

@Composable
fun ProfileDetailBlock() {
    val tabs = listOf(stringResource(id = R.string.tab_photos), stringResource(id = R.string.tab_likes), stringResource(
        id = R.string.tab_item_collections
    ))
    Column(Modifier.padding(top = 30.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            UserAvatarItem(
                modifier = Modifier.size(80.dp),
                url = "https://s.yimg.com/ny/api/res/1.2/.xlEGALHKRabaj7GtNr_2g--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTE0MDE-/https://media.zenfs.com/en/homerun/feed_manager_auto_publish_494/c7db14c56c9f5648abf2b2cbecaa4dbb")

            Column(modifier = Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "9.8K")
                Text(text = "Photos")
            }

            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "14.7K")
                Text(text = "Likes")
            }

            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "90")
                Text(text = "Collections")
            }
        }
        Column {
            Text(text = "Daenerys Targarien")
            Text(text = "New Forest National Park, UK")
            Text(text = "Hobbyist photographer from England, sharing my digital" +
                    "and film photos along with vintage slide scans. Click the" +
                    "Collections tab below to view my images in handy folders")
        }

        TabScreen(tabs = tabs, Color.Transparent) {

        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ShowProfileDetailBlock() {
    ProfileDetailBlock()
}