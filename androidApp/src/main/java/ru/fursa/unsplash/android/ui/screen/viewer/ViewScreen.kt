package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow

@Composable
fun ViewScreen(url: String, navController: NavController) {
    val context = LocalContext.current

    DialogWindow(
        content = {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(url)
                    .crossfade(durationMillis = 1000)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        },
        onDismiss = {
            navController.navigateUp()
        }
    )
}
