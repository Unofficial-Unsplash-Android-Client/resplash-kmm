package ru.fursa.unsplash.android.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.controls.SearchBar
import ru.fursa.unsplash.android.ui.tabs.TabScreen

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navController: NavController
) {
    val tabs = listOf(
        stringResource(id = R.string.tab_photos),
        stringResource(id = R.string.tab_item_collections),
        stringResource(id = R.string.tab_item_users)
    )

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            navController.navigateUp()
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                TopAppBar(
                    modifier = Modifier.wrapContentSize(),
                    backgroundColor = Color.White,
                    elevation = 0.dp
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        "",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { navController.navigateUp() },
                        tint = Color.Black
                    )

                    SearchBar(
                        enabled = true,
                        navController = navController,
                        onSearch = { query ->
                            viewModel.onStartSearch(query)
                        },
                        onClose = {

                        },
                        onNavigateTo = {
                            navController.navigate("search")
                        }
                    )
                }


            }
            TabScreen(tabs = tabs, backgroundColor = Color.White) { pos ->
                when (pos) {
                    0 -> PhotosSearchScreen()
                    1 -> CollectionSearchScreen()
                    2 -> UsersSearchScreen()
                }
            }
        }
    }
}