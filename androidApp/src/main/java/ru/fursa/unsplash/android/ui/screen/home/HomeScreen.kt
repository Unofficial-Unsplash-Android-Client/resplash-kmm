package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.base.screen.ErrorScreen
import ru.fursa.unsplash.android.ui.kit.compound.DotsPreloader
import ru.fursa.unsplash.android.ui.kit.list.HomeList
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.routing.Routes

@Composable
fun HomeScreen(
    screenState: State<HomeMVIContract.State>,
    loadNextItems: (Boolean) -> Unit?
) {
    val navController = rememberNavController()

    when {
        screenState.value.loading -> DotsPreloader(
            circleColor = Color.LightGray,
            circleSize = 12.dp,
            spaceBetween = 15.dp
        )

        screenState.value.error -> ErrorScreen(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            message = screenState.value.message
        )

        screenState.value.success -> {
            HomeList(
                photos = screenState.value.data,
                onNavigateClick = { username ->
                    navController.navigate("${Routes.Profile.name}/$username")
                },
                onViewPhoto = { url ->
                    navController.navigate("${Routes.View.name}/${url.encodeUrl()}")
                },
                onLoadNextItems = { it -> loadNextItems(it) }
            )
        }
    }

    NavGraph(navController = navController)
}
