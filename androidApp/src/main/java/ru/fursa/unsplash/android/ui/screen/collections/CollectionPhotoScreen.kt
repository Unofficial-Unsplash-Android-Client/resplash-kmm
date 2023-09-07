package ru.fursa.unsplash.android.ui.screen.collections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.kit.list.BuildCollectionList
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.routing.Routes

@Composable
fun CollectionPhotoScreen(
    viewModel: CollectionViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    val collections = viewModel.collectionPager.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BuildCollectionList(
            collections = collections,
            onCollectionClicked = { id ->
                navController.navigate("${Routes.Collection.name}/$id")
            }
        )
        NavGraph(navController = navController)
    }
}
