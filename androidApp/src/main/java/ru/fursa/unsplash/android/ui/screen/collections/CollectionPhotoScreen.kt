package ru.fursa.unsplash.android.ui.screen.collections

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CollectionPhotoScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(top = 60.dp)) {
        Text(text = "Collection photo screen")
    }
}
