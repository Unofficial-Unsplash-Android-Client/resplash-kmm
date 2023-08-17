package ru.fursa.unsplash.android.ui.screen.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.fursa.unsplash.android.R

@Composable
fun AuthorizationScreen(navController: NavController) {
    var emailAddress: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { navController.navigateUp() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Spacer(modifier = Modifier.size(20.dp))

            TextField(
                modifier = Modifier
                    .width(390.dp)
                    .wrapContentHeight(),
                label = { Text(text = "Email", fontSize = 13.sp) },
                value = emailAddress,
                onValueChange = { newValue ->
                    emailAddress = newValue
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
            )

            Spacer(modifier = Modifier.size(20.dp))

            TextField(
                modifier = Modifier
                    .width(390.dp)
                    .wrapContentHeight(),
                label = { Text(text = "Password", fontSize = 13.sp) },
                value = password,
                onValueChange = { newValue ->
                    password = newValue
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 8.dp)
            ) {
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate("reset")
                    },
                    text = "Forgot your password",
                    textAlign = TextAlign.Left,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            OutlinedButton(modifier = Modifier
                .width(200.dp)
                .height(45.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            ), onClick = { /*TODO*/ }) {
                Text(text = "Login", color = Color.White)
            }

            Spacer(modifier = Modifier.size(30.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Don't have an account yet?")
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate("registration")
                    },
                    text = "Join",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

        }
    }
}


@Preview
@Composable
fun PreviewAuthScreen() {
    val navController = rememberNavController()
    AuthorizationScreen(navController = navController)
}