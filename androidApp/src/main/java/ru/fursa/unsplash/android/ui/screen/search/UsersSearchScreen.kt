package ru.fursa.unsplash.android.ui.screen.search

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.controls.common.UserSearchCard

@Composable
fun UsersSearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val users = viewModel.userResults.collectAsLazyPagingItems()

    Log.d("Response", users.loadState.toString())
    LazyColumn(content = {
        items(users.itemCount) { index ->
            val item = users[index] ?: return@items
            Text(text = item.toString())
            UserSearchCard(
                profileImageUrl = item.profileImage?.small.orEmpty(),
                username = item.name.orEmpty(),
                instagramAccount = "@ig007"
            )
        }
    })
}


