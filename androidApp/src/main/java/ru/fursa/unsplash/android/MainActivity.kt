package ru.fursa.unsplash.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.fursa.unsplash.android.ui.controls.SearchBar
import ru.fursa.unsplash.android.ui.controls.UserAvatarItem
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.android.ui.tabs.TabScreen
import ru.fursa.unsplash.android.ui.theme.UnsplashApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashApplicationTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitialScreen(navController)
                    NavGraph(navController = navController)
                }
            }

        }
    }

}

@Composable
fun InitialScreen(navController: NavController) {
    val tabs = listOf(
        stringResource(id = R.string.tab_item_home),
        stringResource(id = R.string.tab_item_collections),
    )


    Column() {
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatarItem(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("authorization")
                    },
                url = "https://static.hbo.com/content/dam/hbodata/series/game-of-thrones/character/s5/daenarys-1920.jpg?w=60"
            )
            SearchBar(onSearch = { req ->

            })
        }
        TabScreen(tabs, Color.White) { pageIndex ->
            when (pageIndex) {
                0 -> HomeScreen()
                1 -> CollectionPhotoScreen()
            }
        }

    }

}

