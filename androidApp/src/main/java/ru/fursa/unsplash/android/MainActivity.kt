package ru.fursa.unsplash.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.eventbus.LoadEventBus
import ru.fursa.unsplash.android.ui.kit.compound.SearchBar
import ru.fursa.unsplash.android.ui.kit.image.DefaultUserAvatar
import ru.fursa.unsplash.android.ui.kit.tabs.TabScreen
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeViewModel
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.android.ui.theme.UnsplashApplicationTheme
import ru.fursa.unsplash.routing.Routes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashApplicationTheme {
                val navController = rememberNavController()
                val homeViewModel: HomeViewModel = koinViewModel()
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitialScreen(navController, homeViewModel)
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun InitialScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    val tabs = listOf(
        stringResource(id = R.string.tab_item_home),
        stringResource(id = R.string.tab_item_collections),
    )

    val scope = rememberCoroutineScope()

    Column() {
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultUserAvatar()
            SearchBar(
                navController = navController,
                enabled = false,
                onSearch = { req -> },
                onClose = {
                    scope.launch {
                        LoadEventBus.send(LoadEventBus.Event.CloseSearch)
                    }
                }, onNavigateTo = {
                navController.navigate(Routes.Search.name)
            }
            )
        }
        TabScreen(tabs, Color.White) { pageIndex ->
            when (pageIndex) {
                0 -> {
                    HomeScreen(navController = navController)
                }

                1 -> CollectionPhotoScreen(username = "")
            }
        }
    }
}
