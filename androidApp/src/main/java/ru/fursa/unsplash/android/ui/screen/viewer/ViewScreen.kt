package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.R
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
            Box(modifier = Modifier.fillMaxSize()) {
                when (val state = viewState.value) {
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
                        val message = state.message
                        ErrorScreen(message)
                    }

                    is ViewerViewModel.ImageLoadState.Success -> {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(state.result.drawable)
                                .crossfade(enable = false)
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

                AnimatedVisibility(visible = true) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 20.dp, start = 16.dp),
                        backgroundColor = Color.White,
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Done,
                                contentDescription = "",
                                tint = Color.DarkGray
                            )
                        },
                        text = { Text(text = stringResource(id = R.string.set_wallpaper)) },
                        onClick = {
                            viewModel.setupWallpaper(url, loader)
                        }
                    )
                }
            }
        },
        onDismiss = {
            navController.navigateUp()
        }
    )
}
