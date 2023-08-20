package ru.fursa.unsplash.android.ui.kit.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    title: String,
    onClick: () -> Unit,
) {
    OutlinedButton(modifier = Modifier
        .width(200.dp)
        .height(45.dp), colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Black
    ), onClick = { onClick() }) {
        Text(text = title, color = Color.White)
    }
}