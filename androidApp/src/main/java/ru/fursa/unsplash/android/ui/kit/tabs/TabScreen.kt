package ru.fursa.unsplash.android.ui.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.ui.kit.tabs.TabItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    tabs: List<String>,
    backgroundColor: Color,
    onTabIndexChanged: @Composable (index: Int) -> Unit,
) {
    val scrollableTabIndex = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState(initialPage = 0)

        TabRow(
            selectedTabIndex = scrollableTabIndex.value,
            modifier = Modifier.height(48.dp),
            backgroundColor = backgroundColor,
            indicator = { positions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(positions[scrollableTabIndex.value])
                )
            }
        ) {

            tabs.forEachIndexed { index, title ->
                TabItem(
                    selectedTabIndex = pagerState.currentPage,
                    tabIndex = index,
                    tabTitle = title,
                    pagerState = pagerState
                )
            }
        }

        HorizontalPager(
            pageCount = tabs.size, state = pagerState, modifier = Modifier.fillMaxWidth()
        ) { pageIndex -> onTabIndexChanged(pageIndex) }

        LaunchedEffect(key1 = pagerState.currentPage) {
            scrollableTabIndex.value = pagerState.currentPage
        }
    }
}
