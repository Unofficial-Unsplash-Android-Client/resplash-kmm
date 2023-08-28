package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fursa.unsplash.android.R

@Composable
fun LogoArea() {
    Image(
        painterResource(id = R.drawable.ic_download),
        modifier = Modifier
            .width(60.dp)
            .height(60.dp),
        contentDescription = null
    )
    Spacer(modifier = Modifier.size(30.dp))
    Text(
        text = "Login to your Unsplash account to connect Unsplash app",
        style = TextStyle(
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal
        )
    )
}
