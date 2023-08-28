package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Screen(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        content()
    }
}
