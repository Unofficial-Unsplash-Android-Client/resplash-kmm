package ru.fursa.unsplash.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.tabs.TabScreen
import ru.fursa.unsplash.android.ui.theme.UnsplashApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashApplicationTheme {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitialScreen()
                }
            }

        }
    }

}
@Composable
fun InitialScreen() {
    val tabs = listOf(
        stringResource(id = R.string.tab_item_home),
        stringResource(id = R.string.tab_item_collections)
    )

    Column() {
        Spacer(modifier = Modifier.size(20.dp))
        SearchBar(onSearch = {})
        Spacer(modifier = Modifier.size(20.dp))
        TabScreen(tabs, Color.White) { pageIndex ->
            when (pageIndex) {
                0 -> HomeScreen()
                1 -> CollectionPhotoScreen()
            }
        }
    }
}

