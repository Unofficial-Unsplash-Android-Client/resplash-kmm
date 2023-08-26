package ru.fursa.unsplash.android.base.screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.compound.Screen

@Composable
fun ErrorScreen() {
    Screen {
        Image(
            painter = painterResource(id = R.drawable.ic_no_search_results),
            contentDescription = ""
        )
    }
}