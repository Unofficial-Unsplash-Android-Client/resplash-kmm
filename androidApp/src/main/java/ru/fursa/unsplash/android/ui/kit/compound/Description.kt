package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Description(
    count: String,
    author: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = count)
        Text(text = "•", fontSize = 30.sp)
        Text(text = "Author: $author")
    }
}
