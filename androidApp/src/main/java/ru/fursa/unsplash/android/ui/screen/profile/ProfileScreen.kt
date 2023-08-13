package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.controls.UserAvatarItem
import ru.fursa.unsplash.android.ui.controls.common.ProfileCounter
import ru.fursa.unsplash.android.ui.controls.common.ProfileDescription
import ru.fursa.unsplash.android.ui.tabs.TabScreen

@Composable
@UiComposable
fun ProfileScreen(
    navController: NavController,
) {
    val tabs = listOf(
        stringResource(id = R.string.tab_photos),
        stringResource(id = R.string.tab_likes),
        stringResource(id = R.string.tab_item_collections)
    )

    Dialog(properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = { }) {

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopAppBar(
                modifier = Modifier.wrapContentSize(),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    "",
                    modifier = Modifier.clickable { navController.navigateUp() },
                    tint = Color.Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(16.dp)
            ) {
                UserAvatarItem(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 3.dp),
                    url = "https://static.hbo.com/content/dam/hbodata/series/game-of-thrones/character/s5/daenarys-1920.jpg?w=60"
                )

                ProfileCounter(
                    modifier = Modifier.padding(18.dp), counter = "12", subject = "Фотографий"
                )

                ProfileCounter(
                    modifier = Modifier.padding(16.dp), counter = "200", subject = "Лайков"
                )

                ProfileCounter(
                    modifier = Modifier.padding(16.dp), counter = "3", subject = "Коллекции"
                )
            }

            ProfileDescription(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 8.dp),
                fullName = "Daenerys Targarien",
                location = "London, UK",
                bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            )

            TabScreen(tabs = tabs, Color.White) { tabIndex ->
                when(tabIndex) {
                    0 -> UserPhotosScreen()
                    1 -> UserCollectionsScreen()
                    2 -> UserLikesScreen()
                }
            }
        }
    }
}
