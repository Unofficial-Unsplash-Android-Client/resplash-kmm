package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun UserPhotosScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "ru.fursa.unsplash.data.api.models.search.User photos screen")
    }
}