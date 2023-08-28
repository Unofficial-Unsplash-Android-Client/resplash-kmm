package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.routing.Routes

@Composable
fun ForgotPassword(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 8.dp)
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(Routes.Reset.name)
            },
            text = stringResource(id = R.string.forgot_password),
            textAlign = TextAlign.Left,
            color = Color.Gray,
            fontSize = 14.sp,
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            )
        )
    }
}
