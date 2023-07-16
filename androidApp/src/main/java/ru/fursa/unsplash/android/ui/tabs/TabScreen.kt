package ru.fursa.unsplash.android.ui.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.single.SinglePhotoScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Single", "Collections")
    val scrollableTabIndex = remember { mutableStateOf(selectedTabIndex) }

    Column(modifier = Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState(initialPage = selectedTabIndex)

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.height(48.dp),
            edgePadding = 16.dp,
            indicator = { positions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(positions[scrollableTabIndex.value])
                )
            }) {

            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = scrollableTabIndex.value == index,
                    onClick = {
                        scrollableTabIndex.value = index
                        selectedTabIndex = index
                    },
                    text = { Text(text = title) },
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )
            }

        }

        HorizontalPager(
            pageCount = tabs.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) {
            when (it) {
                0 -> SinglePhotoScreen()
                1 -> CollectionPhotoScreen()
            }
        }

        LaunchedEffect(key1 = pagerState.currentPage, block = {
            scrollableTabIndex.value = pagerState.currentPage
        })

    }
}

