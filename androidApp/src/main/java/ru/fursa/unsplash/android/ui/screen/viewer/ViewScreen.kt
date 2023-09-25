package ru.fursa.unsplash.android.ui.screen.viewer

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.ImageLoader
import coil.compose.AsyncImage
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
    val context = LocalContext.current
    val viewState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = url, block = {
        val request = ImageRequest.Builder(context)
            .data(url)
            .listener(
                onStart = {
                    viewModel.handleEvent(ViewerMVIContract.Event.Loading)
                },
                onSuccess = { _, result ->
                    viewModel.handleEvent(ViewerMVIContract.Event.Success(result))
                },
                onError = { _, error ->
                    val message = error.throwable.message.orEmpty()
                    viewModel.handleEvent(ViewerMVIContract.Event.Error(message))
                }
            )
            .build()
        val loader = ImageLoader(context)
        loader.execute(request)

        viewModel.effect.collect { effect ->
            when (effect) {
                is ViewerMVIContract.Effect.ShowToastMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    })

    DialogWindow(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                when {
                    viewState.value.isError -> {
                        ErrorScreen(message = viewState.value.errorMessage)
                    }

                    viewState.value.isLoading -> {
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

                    viewState.value.isSuccess -> {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(viewState.value.pictureDrawable)
                                .crossfade(enable = false)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 20.dp, start = 16.dp),
                    visible = viewState.value.isSetWallpaperButtonVisible
                ) {
                    ExtendedFloatingActionButton(
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
                            viewModel.handleEvent(ViewerMVIContract.Event.OnClickSetWallpaper(url))
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
