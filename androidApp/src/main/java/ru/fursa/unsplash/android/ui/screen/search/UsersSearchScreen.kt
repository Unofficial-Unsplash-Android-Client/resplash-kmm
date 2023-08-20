package ru.fursa.unsplash.android.ui.screen.search

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.UserSearchCard

@Composable
fun UsersSearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val users = viewModel.userResults.collectAsLazyPagingItems()

    Log.d("Response", users.loadState.toString())
    LazyColumn(content = {
        items(users.itemCount) { index ->
            val item = users[index] ?: return@items
            UserSearchCard(
                profileImageUrl = item.profileImage.medium,
                username = item.username.toString(),
                instagramAccount = item.instagramUsername.orEmpty(),
                photos = item.photos
            )
        }
    })
}



