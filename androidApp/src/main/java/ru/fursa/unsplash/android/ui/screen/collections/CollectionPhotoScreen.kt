package ru.fursa.unsplash.android.ui.screen.collections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.api.CollectionHttpResponse

@Composable
fun CollectionPhotoScreen(viewModel: CollectionViewModel = koinViewModel()) {
    val state = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val currentState = state.value) {
            is CollectionViewModel.UiState.SuccessState -> {
                CreateCollectionsList(collections = (currentState.data))
            }
            else -> Unit
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadCollections()
    }
}

@Composable
fun CreateCollectionsList(collections: List<CollectionHttpResponse>) {
    LazyColumn {
        collections.forEach {
            item {
                ListItem(collections = it)
            }
        }
    }
}

@Composable
fun ListItem(collections: CollectionHttpResponse) {
    Text(text = collections.title.toString())
}
