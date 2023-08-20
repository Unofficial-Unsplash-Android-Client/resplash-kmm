package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.base.Photo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val photosPagingItems: LazyPagingItems<Photo> = viewModel.screenState.collectAsLazyPagingItems()

    NavGraph(navController = navController)
    BuildHomeList(
        navController = navController,
        photos = photosPagingItems
    )
}




