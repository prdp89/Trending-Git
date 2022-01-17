package com.example.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SearchUi(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onBackKeyPressed: (Pair<TextFieldValue, Boolean>) -> Unit,
    onSearchBegin: (TextFieldValue) -> Unit,
    searchTextPlaceholder: String,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
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
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onSearchFocusChange(it.isFocused)
                    }
                    .focusRequester(focusRequester),
                textStyle = TextStyle(color = MaterialTheme.colors.onSecondary, fontSize = 18.sp),
                leadingIcon = {
                    if (!searchFocused) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    } else {
                        IconButton(
                            onClick = {
                                onBackKeyPressed(Pair(TextFieldValue(""), false))
                                    focusManager.clearFocus()
                            }
                        ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }

                },
                placeholder = { if (query.text.isEmpty()) Text(text = searchTextPlaceholder) },
                trailingIcon = {
                    if (query != TextFieldValue("")) {
                        IconButton(
                            onClick = {
                                onQueryChange(TextFieldValue(""))
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                },
                keyboardActions = KeyboardActions(onDone = {
                    onSearchBegin(TextFieldValue(query.text))
                    focusManager.clearFocus()
                }),
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
