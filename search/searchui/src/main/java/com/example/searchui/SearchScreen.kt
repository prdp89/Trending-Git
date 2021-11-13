package com.example.searchui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Repos") },
                elevation = 8.dp,
                actions = {

                })
        }, content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)) {
                    Text(text = "Search here..")
                }
        }
    )
}