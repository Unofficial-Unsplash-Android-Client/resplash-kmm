package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun UserLikesScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Items liked by user")
    }
}