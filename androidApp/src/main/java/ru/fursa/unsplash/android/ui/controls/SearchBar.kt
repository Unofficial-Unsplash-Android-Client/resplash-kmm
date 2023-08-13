package ru.fursa.unsplash.android.ui.controls

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        singleLine = true,
        shape = RoundedCornerShape(size = 20.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 12.sp),
        onValueChange = { text = it },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        text = ""
                    })
            }
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier
            .padding(3.dp)
            .clip(CircleShape)
            .clickable {}) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 16.dp, end = 16.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            keyboardController?.hide()
            focusManager.clearFocus()
        }, onDone = {
            Log.d("Search", "Done")
        })
    )
}

@Composable
@Preview(showSystemUi = true)
fun PreviewSearchBar() {
    SearchBar(
        onSearch = { text ->

        })
}