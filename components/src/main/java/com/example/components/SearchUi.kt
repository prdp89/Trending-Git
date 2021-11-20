package com.example.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SearchUi(state: MutableState<TextFieldValue>, searchTextPlaceholder : String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 12.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = state.value,
                onValueChange = { value ->
                    state.value = value
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = MaterialTheme.colors.onSecondary, fontSize = 18.sp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                },
                placeholder = { Text(text = searchTextPlaceholder) },
                trailingIcon = {
                    if (state.value != TextFieldValue("")) {
                        IconButton(
                            onClick = {
                                state.value =
                                    TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSecondary,
                    cursorColor = MaterialTheme.colors.onSecondary,
                    leadingIconColor = MaterialTheme.colors.onSecondary,
                    trailingIconColor = MaterialTheme.colors.onSecondary,
                    backgroundColor = MaterialTheme.colors.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}