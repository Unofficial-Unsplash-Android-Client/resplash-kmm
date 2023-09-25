package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildCollectionList
import ru.fursa.unsplash.routing.Routes

@Composable
fun UserCollectionsScreen(
    username: String,
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {
    Screen(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val collections = viewModel.userCollections.collectAsLazyPagingItems()

        BuildCollectionList(collections = collections, onCollectionClicked = { id, author, count ->
            navController.navigate("${Routes.Collection.name}/$id/$username/$count")
        })

        LaunchedEffect(key1 = username, block = {
            viewModel.getUserCollections(username)
        })
    }
}
