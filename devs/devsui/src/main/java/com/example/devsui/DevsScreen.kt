package com.example.devsui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.components.SearchUi
import com.example.trending.dev.TrendingDev

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DevsScreen() {
    val itemList = listOf(
        TrendingDev(
            1,
            "Pardeep Sharma",
            "Sr. Software engineer",
            "Core Skills: Kotlin, KMM, Compose and Adavance Java"
        ),
        TrendingDev(
            2,
            "Sandeep Sharma",
            "Tech Lead",
            "Core Skills: C#, ASP .Net, SQL"
        )
    )
    Scaffold(content = {
            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                /*SearchUi(textState, stringResource(id = R.string.search_devs)) {

                }*/
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