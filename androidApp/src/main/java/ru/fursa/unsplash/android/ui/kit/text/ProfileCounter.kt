package ru.fursa.unsplash.android.ui.kit.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCounter(
    modifier: Modifier = Modifier,
    counter: String,
    subject: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = counter,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        )
        Text(
            text = subject,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        )
    }
}

@Preview
@Composable
fun PreviewProfileCounter() {
    Box(modifier = Modifier.background(Color.White)) {
        ProfileCounter(counter = "10", subject = "Фотографий")
    }
}
