package com.example.devsui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.SearchUi
import com.example.trending.dev.TrendingDev

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DevsScreen() {
    val itemList = listOf(
        TrendingDev(
            1,
            "Fresh Vegges and Greens",
            "Very awesome list item has very awesome subtitle. This is bit long",
            "R.drawable.food1"
        ),
        TrendingDev(
            2,
            "Best blue berries",
            "Very awesome list item has very awesome subtitle. This is bit long",
            "R.drawable.food2"
        )
    )
    Scaffold(content = {
            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchUi(textState, stringResource(id = R.string.search_devs))
                LazyColumn {
                    items(itemList) {
                            item ->
                        DevListItem(devItem = item) {
                            //on click..
                        }
                    }
                }
            }
        }
    )
}