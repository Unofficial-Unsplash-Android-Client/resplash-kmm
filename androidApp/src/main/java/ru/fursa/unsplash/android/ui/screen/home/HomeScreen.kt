package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.data.ui.models.PhotoModel
import ru.fursa.unsplash.routing.Routes

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val photosPagingItems: LazyPagingItems<PhotoModel> = viewModel.screenState
        .collectAsLazyPagingItems()

    NavGraph(navController = navController)
    BuildHomeList(
        photos = photosPagingItems,
        onNavigateClick = { username ->
            navController.navigate("${Routes.Profile.name}/$username")
        },
        onViewPhoto = { url ->
            navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
        }
    )
}
