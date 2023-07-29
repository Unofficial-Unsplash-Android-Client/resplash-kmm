package ru.fursa.unsplash.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.single.SinglePhotoScreen
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
    TabScreen { pageIndex ->
        when (pageIndex) {
            0 -> SinglePhotoScreen()
            1 -> CollectionPhotoScreen()
        }
    }

}

