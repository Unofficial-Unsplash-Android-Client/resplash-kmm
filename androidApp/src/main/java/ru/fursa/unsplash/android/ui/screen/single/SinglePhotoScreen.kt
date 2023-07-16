package ru.fursa.unsplash.android.ui.screen.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SinglePhotoScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp)) {
        Text(text = "Single photo screen")
    }
}
