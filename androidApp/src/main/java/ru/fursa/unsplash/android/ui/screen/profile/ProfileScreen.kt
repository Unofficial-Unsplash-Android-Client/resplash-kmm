package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.image.UserAvatarItem
import ru.fursa.unsplash.android.ui.kit.tabs.TabScreen
import ru.fursa.unsplash.android.ui.kit.text.ProfileCounter
import ru.fursa.unsplash.android.ui.kit.text.ProfileDescription
import ru.fursa.unsplash.base.repository.CurrentUser

@Composable
@UiComposable
fun ProfileScreen(
    username: String,
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = username) {
        viewModel.getUserProfile(username)
    }

    val currentUser = viewModel.currentUser.collectAsState(initial = CurrentUser())

    val tabs = listOf(
        stringResource(id = R.string.user_tab_photo),
        stringResource(id = R.string.user_tab_likes),
        stringResource(id = R.string.user_tab_collection)
    )

    Dialog(properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = { }) {

        Screen(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
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
                Spacer(modifier = Modifier.size(30.dp))
                Text(text = currentUser.value.username)
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
                    avatarUrl = currentUser.value.userAvatarUrl
                )

                ProfileCounter(
                    modifier = Modifier.padding(18.dp),
                    counter = currentUser.value.totalPhotos.toString(),
                    subject = stringResource(id = R.string.photos)
                )

                ProfileCounter(
                    modifier = Modifier.padding(16.dp),
                    counter = currentUser.value.totalLikes.toString(),
                    subject = stringResource(id = R.string.likes)
                )

                ProfileCounter(
                    modifier = Modifier.padding(16.dp),
                    counter = currentUser.value.totalCollections.toString(),
                    subject = stringResource(id = R.string.collections)
                )
            }

            ProfileDescription(
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = 16.dp,
                    bottom = 8.dp
                ),
                fullName = currentUser.value.fullName,
                location = currentUser.value.location,
                bio = currentUser.value.bio
            )
            TabScreen(tabs = tabs, backgroundColor = Color.White) { pos ->
                when (pos) {
                    0 -> UserPhotosScreen(username = username, navController = navController)
                    1 -> UserLikesScreen(username = username, navController = navController)
                    2 -> UserCollectionsScreen(username = username, navController = navController)
                }
            }
        }
    }
}
