package ru.fursa.unsplash.android.ui.screen.search

import androidx.compose.runtime.Composable
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

    Screen {
        BuildCollectionList(
            collections = collectionItems,
            onCollectionClicked = { id, author, count -> }
        )
    }
}
