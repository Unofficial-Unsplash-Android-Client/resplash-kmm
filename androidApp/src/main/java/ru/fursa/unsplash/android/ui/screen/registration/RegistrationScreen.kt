package ru.fursa.unsplash.android.ui.screen.registration

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.ui.kit.button.ActionButton
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.text.EmailTextField
import ru.fursa.unsplash.android.ui.kit.text.PasswordTextField

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    var firstName: String by remember { mutableStateOf("") }
    var lastName: String by remember { mutableStateOf("") }
    var userName: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Screen {
        Text(
            text = stringResource(id = R.string.join_unsplash),
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextField(
            modifier = Modifier
                .width(390.dp)
                .wrapContentHeight(),
            label = { Text(text = stringResource(id = R.string.first_name), fontSize = 13.sp) },
            value = firstName,
            onValueChange = { newValue ->
                firstName = newValue
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextField(
            modifier = Modifier
                .width(390.dp)
                .wrapContentHeight(),
            label = { Text(text = stringResource(id = R.string.last_name), fontSize = 13.sp) },
            value = lastName,
            onValueChange = { newValue ->
                lastName = newValue
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Spacer(modifier = Modifier.size(8.dp))
        EmailTextField(emailAddress = email, onEmailChanged = { email -> })
        Spacer(modifier = Modifier.size(8.dp))
        PasswordTextField(passwordValue = password, onPasswordChanged = { newPass -> })

        Spacer(modifier = Modifier.size(30.dp))

        ActionButton(title = stringResource(id = R.string.sign_up), onClick = { })
    }
}
