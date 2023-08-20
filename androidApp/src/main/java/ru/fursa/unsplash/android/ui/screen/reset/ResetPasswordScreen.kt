package ru.fursa.unsplash.android.ui.screen.reset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow
import ru.fursa.unsplash.android.ui.kit.text.EmailTextField

@Composable
fun ResetPasswordScreen(navController: NavController) {
    var email: String by remember { mutableStateOf("") }

    DialogWindow(
        onDismiss = { navController.navigateUp() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                EmailTextField(emailAddress = email, onEmailChanged = { })

                Spacer(modifier = Modifier.size(30.dp))

                OutlinedButton(modifier = Modifier
                    .width(200.dp)
                    .height(45.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                ), onClick = { /*TODO*/ }) {
                    Text(text = "Reset password", color = Color.White)
                }
            }
        })
}