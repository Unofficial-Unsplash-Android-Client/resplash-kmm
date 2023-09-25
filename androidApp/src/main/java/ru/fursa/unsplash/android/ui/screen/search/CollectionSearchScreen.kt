package ru.fursa.unsplash.android.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildCollectionList

@Composable
fun CollectionSearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val collectionItems = viewModel.collectionsResults.collectAsLazyPagingItems()

    Screen(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BuildCollectionList(
            collections = collectionItems,
            onCollectionClicked = { id, author, count -> }
        )
    }
}
