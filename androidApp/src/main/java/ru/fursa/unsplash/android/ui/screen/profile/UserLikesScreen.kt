package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList

@Composable
fun UserLikesScreen(username: String, viewModel: ProfileViewModel = koinViewModel()) {
    Screen {
        val photos = viewModel.userLikedPhotos.collectAsLazyPagingItems()

        BuildHomeList(photos = photos, onNavigateClick = {}, onViewPhoto = {})

        LaunchedEffect(key1 = username, block = {
            viewModel.getUserLikedPhotos(username)
        })
    }
}
