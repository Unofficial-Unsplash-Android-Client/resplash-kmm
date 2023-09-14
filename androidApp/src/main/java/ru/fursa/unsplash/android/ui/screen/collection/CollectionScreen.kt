package ru.fursa.unsplash.android.ui.screen.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.ui.kit.compound.Description
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList
import ru.fursa.unsplash.routing.Routes

@Composable
fun CollectionScreen(
    viewModel: CollectionPhotosViewModel = koinViewModel(),
    navController: NavController,
    id: String,
    authorName: String,
    photoCount: Int,
) {
    val viewState = viewModel.collectionState.collectAsLazyPagingItems()

    LaunchedEffect(key1 = id, block = {
        viewModel.getCollection(id = id)
    })
    DialogWindow(content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopAppBar(
                modifier = Modifier.wrapContentSize(),
                backgroundColor = Color.White,
                elevation = 0.dp,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        "",
                        modifier = Modifier.clickable { navController.navigateUp() },
                        tint = Color.Black
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_world),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                            }
                    )
                }
            }
            Description(
                count = pluralStringResource(id = R.plurals.photos, photoCount, photoCount),
                author = authorName,
                modifier = Modifier.fillMaxWidth()
            )

            BuildHomeList(
                photos = viewState,
                onNavigateClick = { username ->
                    navController.navigate("${Routes.Profile.name}/$username")
                }, onViewPhoto = { url ->
                navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
            }
            )
        }
    }) {
        navController.navigateUp()
    }
}
