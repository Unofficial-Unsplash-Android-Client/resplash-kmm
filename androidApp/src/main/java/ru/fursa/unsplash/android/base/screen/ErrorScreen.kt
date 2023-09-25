package ru.fursa.unsplash.android.base.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.compound.Screen

@Composable
fun ErrorScreen(message: String) {
    Screen(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(40.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_no_search_results),
            contentDescription = ""
        )
    }
}
