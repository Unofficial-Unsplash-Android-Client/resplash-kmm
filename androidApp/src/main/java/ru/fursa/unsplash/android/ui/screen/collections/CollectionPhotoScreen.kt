package ru.fursa.unsplash.android.ui.screen.collections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.controls.collection.CollectionCard
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse

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

        CreateCollectionsList(collections = collections, navController = navController)
        NavGraph(navController = navController)
    }

}

@Composable
fun CreateCollectionsList(
    collections: LazyPagingItems<CollectionResponse>,
    navController: NavController
) {
    LazyColumn {
        items(count = collections.itemCount) { index ->
            val item = collections[index] ?: return@items
            ListItem(collections = item, onNavigationClick = {
                navController.navigate("profile")
            })
        }
    }
}

@Composable
fun ListItem(collections: CollectionResponse, onNavigationClick: () -> Unit) {
    CollectionCard(
        collections.coverPhoto.urls.rawUrl,
        collections.title.orEmpty(),
        collections.totalPhotos,
        onClick = onNavigationClick
    )
}
