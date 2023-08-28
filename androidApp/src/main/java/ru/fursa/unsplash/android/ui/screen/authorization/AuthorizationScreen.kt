package ru.fursa.unsplash.android.ui.screen.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.button.ActionButton
import ru.fursa.unsplash.android.ui.kit.compound.DialogWindow
import ru.fursa.unsplash.android.ui.kit.compound.ForgotPassword
import ru.fursa.unsplash.android.ui.kit.compound.LogoArea
import ru.fursa.unsplash.android.ui.kit.compound.RegistrationField
import ru.fursa.unsplash.android.ui.kit.text.EmailTextField
import ru.fursa.unsplash.android.ui.kit.text.PasswordTextField

@Composable
fun AuthorizationScreen(navController: NavController) {
    val emailAddress: String by remember { mutableStateOf("") }
    val password: String by remember { mutableStateOf("") }

    DialogWindow(
        onDismiss = { navController.navigateUp() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoArea()
                Spacer(modifier = Modifier.size(20.dp))
                EmailTextField(emailAddress = emailAddress) { newEmail -> }
                Spacer(modifier = Modifier.size(20.dp))
                PasswordTextField(passwordValue = password) { newPass -> }
                ForgotPassword(navController = navController)
                Spacer(modifier = Modifier.size(30.dp))
                ActionButton(
                    title = stringResource(id = R.string.login),
                    onClick = { }
                )
                Spacer(modifier = Modifier.size(30.dp))
                RegistrationField(navController = navController)
            }
        })
}
