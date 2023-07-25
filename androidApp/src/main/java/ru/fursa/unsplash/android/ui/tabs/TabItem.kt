package ru.fursa.unsplash.android.ui.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabItem(
    selectedTabIndex: Int,
    tabIndex: Int,
    tabTitle: String,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    Tab(selected = selectedTabIndex == tabIndex, onClick = {
        scope.launch {
            pagerState.animateScrollToPage(tabIndex)
        }
    }, text = {
        Text(text = tabTitle)
    })

}