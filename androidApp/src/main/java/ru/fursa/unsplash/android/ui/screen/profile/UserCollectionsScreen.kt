package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildCollectionList

@Composable
fun UserCollectionsScreen(username: String, viewModel: ProfileViewModel = koinViewModel()) {
    Screen {
        val collections = viewModel.userCollections.collectAsLazyPagingItems()
        val navController = rememberNavController()

        BuildCollectionList(collections = collections, navController = navController)

        LaunchedEffect(key1 = username, block = {
            viewModel.getUserCollections(username)
        })
    }
}