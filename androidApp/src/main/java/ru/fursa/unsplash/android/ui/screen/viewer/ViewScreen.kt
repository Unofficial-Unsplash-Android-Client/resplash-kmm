package ru.fursa.unsplash.android.ui.screen.viewer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewScreen(
    photoId: String,
    url: String,
    username: String,
    avatarUrl: String,
    navController: NavController,
    viewModel: ViewerViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val viewState = viewModel.uiState.collectAsState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    LaunchedEffect(key1 = photoId, block = {
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

        viewModel.handleEvent(ViewerMVIContract.Event.OnLoadPhotoInfo(photoId = photoId))

        viewModel.effect.collect { effect ->
            when (effect) {
                is ViewerMVIContract.Effect.ShowToastMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    })

    BottomSheet(
        viewState = viewState,
        state = bottomSheetScaffoldState,
        url = url,
        avatarUrl = avatarUrl,
        username = username,
        navController = navController,
        context = context,
        onClickSetWallpaper = { url ->
            viewModel.handleEvent(ViewerMVIContract.Event.OnClickSetWallpaper(url))
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun BottomSheet(
    viewState: State<ViewerMVIContract.State>,
    state: BottomSheetScaffoldState,
    url: String,
    avatarUrl: String,
    username: String,
    navController: NavController,
    context: Context,
    onClickSetWallpaper: (String) -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = state,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(size = 48.dp))
                    .background(Color.White)
            ) {
                Divider(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .width(60.dp)
                        .padding(top = 8.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    color = Color.LightGray,
                    thickness = 6.dp
                )
                ProfileInfoHeader(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                    username = username,
                    avatarUrl = avatarUrl,
                    onDownloadClick = { url -> },
                    onLikeClick = { }
                )
                Divider(
                    modifier = Modifier.padding(top = 80.dp),
                    thickness = 1.dp,
                    color = Color.LightGray.copy(alpha = 0.7f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 90.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.watched),
                            fontWeight = FontWeight.Medium
                        )
                        Text(text = "${viewState.value.photoInfoModel?.viewCount}")
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.downloads),
                            fontWeight = FontWeight.Medium
                        )
                        Text(text = "${viewState.value.photoInfoModel?.downloadsCount}")
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.likes),
                            fontWeight = FontWeight.Medium
                        )
                        Text(text = "${viewState.value.photoInfoModel?.likesCount}")
                    }
                }

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(all = 16.dp)
                        .align(Alignment.BottomEnd),
                    backgroundColor = Color.Black,
                    text = {
                        Text(
                            color = Color.White,
                            text = stringResource(id = R.string.set_wallpaper)
                        )
                    },
                    onClick = {
                        onClickSetWallpaper(url)
                    }
                )
            }
        },
        sheetPeekHeight = 30.dp,
    ) {
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
                    Box(modifier = Modifier.fillMaxSize()) {
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
            }
        }
    }
}
