package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.base.screen.ErrorScreen
import ru.fursa.unsplash.android.ui.kit.compound.DotsPreloader
import ru.fursa.unsplash.android.ui.kit.list.HomeList
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.routing.Routes

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadItems()
    })

    when {
        uiState.loading -> DotsPreloader(
            circleColor = Color.LightGray,
            circleSize = 12.dp,
            spaceBetween = 15.dp
        )
        uiState.errorMessage != null -> ErrorScreen(message = "Oops something went wrong!!!")
        uiState.items.isNotEmpty() -> {
            HomeList(
                photos = uiState.items,
                onNavigateClick = { username ->
                    navController.navigate("${Routes.Profile.name}/$username")
                },
                onViewPhoto = { url ->
                    navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
                }
            )
        }
    }

    NavGraph(navController = navController)
}
