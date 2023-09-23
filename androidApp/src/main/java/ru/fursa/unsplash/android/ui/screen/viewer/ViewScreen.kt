package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.screen.ErrorScreen
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow
import ru.fursa.unsplash.android.ui.kit.compound.DotsPreloader

@Composable
fun ViewScreen(
    url: String,
    navController: NavController,
    viewModel: ViewerViewModel = koinViewModel()
) {
    val loader = LocalImageLoader.current
    val context = LocalContext.current
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = url, block = {
        viewModel.loadImage(url, loader)
    })

    DialogWindow(
        content = {
            when (viewState.value) {
                ViewerViewModel.ImageLoadState.Loading -> {
                    DialogWindow(content = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            DotsPreloader(
                                circleColor = Color.LightGray,
                                circleSize = 12.dp,
                                spaceBetween = 15.dp
                            )
                        }
                    }) {
                        navController.navigateUp()
                    }
                }

                is ViewerViewModel.ImageLoadState.ErrorLoading -> {
                    val message = (viewState.value as? ViewerViewModel.ImageLoadState.ErrorLoading)
                        ?.message.orEmpty()
                    ErrorScreen(message)
                }

                is ViewerViewModel.ImageLoadState.Success -> {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(
                                (viewState.value as ViewerViewModel.ImageLoadState.Success)
                                    .result.drawable
                            )
                            .crossfade(durationMillis = 1000)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                else -> Unit
            }
        },
        onDismiss = {
            navController.navigateUp()
        }
    )
}
