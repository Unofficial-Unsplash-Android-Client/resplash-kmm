package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList

@Composable
fun UserPhotosScreen(
    username: String,
    viewModel: ProfileViewModel = koinViewModel()
) {
    Screen {
        val photos = viewModel.userPhotos.collectAsLazyPagingItems()

        BuildHomeList(photos = photos, onNavigateClick = {})

        LaunchedEffect(key1 = username) {
            viewModel.getUserPhotos(username)
        }
    }
}
