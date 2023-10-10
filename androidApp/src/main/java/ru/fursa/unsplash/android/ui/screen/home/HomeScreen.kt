package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.base.screen.ErrorScreen
import ru.fursa.unsplash.android.ui.kit.compound.DotsPreloader
import ru.fursa.unsplash.android.ui.kit.list.HomeList
import ru.fursa.unsplash.routing.Routes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.collectAsState()

    val pullToRefreshState = rememberPullRefreshState(
        refreshing = uiState.value.refresh,
        onRefresh = { viewModel.loadItems(forceReload = true) }
    )

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadItems()
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pullRefresh(pullToRefreshState),
        contentAlignment = Alignment.Center
    ) {
        PullRefreshIndicator(
            refreshing = uiState.value.refresh,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        when {
            uiState.value.loading -> DotsPreloader(
                circleColor = Color.LightGray,
                circleSize = 12.dp,
                spaceBetween = 15.dp
            )

            uiState.value.error -> ErrorScreen(
                message = uiState.value.message,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )

            uiState.value.success -> HomeList(
                photos = uiState.value.data,
                onNavigateClick = { username ->
                    navController.navigate("${Routes.Profile.name}/$username")
                },
                onViewPhoto = { url ->
                    navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
                },
                onLoadNextItems = { viewModel.loadNextItems() }
            )
        }
    }
}
