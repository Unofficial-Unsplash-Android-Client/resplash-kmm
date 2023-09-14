package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow

@Composable
fun ViewScreen(
    url: String,
    navController: NavController,
    viewModel: ViewerViewModel = koinViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = url, block = {
        viewModel.sideEffect.collectLatest { sideEffect ->
        }
    })

    DialogWindow(
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(url)
                        .crossfade(durationMillis = 1000)
                        .listener(
                            onStart = {
                                viewModel.obtainEvent(ViewerViewModel.ViewerEvent.Loading)
                            }, onSuccess = { _, _ ->
                            viewModel.obtainEvent(ViewerViewModel.ViewerEvent.OnSuccess)
                        }, onError = { _, err ->
                            viewModel.obtainEvent(
                                ViewerViewModel.ViewerEvent.OnError(
                                    err.throwable.message.toString()
                                )
                            )
                        }
                        )
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 30.dp, start = 16.dp),
                    text = { Text(text = stringResource(id = R.string.set_wallpaper)) },
                    onClick = {
                        viewModel.obtainEvent(ViewerViewModel.ViewerEvent.OnSetWallpaper(url))
                    },
                    backgroundColor = Color.White
                )
            }
        },
        onDismiss = {
            navController.navigateUp()
        }
    )
}
