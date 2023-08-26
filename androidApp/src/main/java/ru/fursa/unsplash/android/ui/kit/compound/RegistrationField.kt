package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.routing.Routes

@Composable
fun RegistrationField(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.dont_have_account))
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(Routes.Registration.name)
            },
            text = stringResource(id = R.string.join),
            fontSize = 14.sp,
            color = Color.Gray,
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            )
        )
    }
}