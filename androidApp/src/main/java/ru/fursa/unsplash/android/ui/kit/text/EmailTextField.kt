package ru.fursa.unsplash.android.ui.kit.text

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailTextField(
    emailAddress: String,
    onEmailChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .width(390.dp)
            .wrapContentHeight(),
        label = { Text(text = "Email", fontSize = 13.sp) },
        value = emailAddress,
        onValueChange = { newValue -> onEmailChanged(newValue) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
    )
}
