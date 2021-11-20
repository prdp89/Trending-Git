package com.example.favoriteui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(com.example.favoriteui.R.string.fav_repos)) },
                elevation = 8.dp,
                actions = {

                })
        }, content = {
           Box(modifier = Modifier
               .fillMaxSize()
               .background(color = MaterialTheme.colors.background))
             {
                 Column (
                     modifier = Modifier.fillMaxSize(),
                     verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally
                         ){
                     Text(text = "No Fav here.. Add some..")
                 }
                 
                 //Text(text = "hello", Modifier.fillMaxSize().background(color = Color.Blue))
                }
        }
    )
}