package ru.fursa.unsplash.android.ui.kit.compound

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.fursa.unsplash.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    navController: NavController,
    enabled: Boolean = false,
    onSearch: (String) -> Unit,
    onClose: () -> Unit,
    onNavigateTo: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 12.sp),
        onValueChange = { text = it },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        text = ""
                        onClose.invoke()
                    })
            }
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier
                .padding(3.dp)
                .clip(CircleShape)
                .clickable {})
        },
        shape = RoundedCornerShape(size = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(size = 20.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onNavigateTo() }
            ),
        placeholder = { Text(text = stringResource(id = R.string.search_hint), fontSize = 12.sp) },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            keyboardController?.hide()
            focusManager.clearFocus()
        }, onDone = { })
    )
}

@Composable
@Preview(showSystemUi = true)
fun PreviewSearchBar() {
    val navController = rememberNavController()
    SearchBar(
        navController = navController,
        onSearch = { text ->

        }, onClose = {

        }, onNavigateTo = {

        })
}