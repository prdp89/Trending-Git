package com.example.searchui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.settings.SettingsViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Repos") },
                elevation = 8.dp,
                actions = {
                    DarkTheme(darkThemeFlow = viewModel.darkTheme) {
                        viewModel.changeTheme(it)
                    }
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


@Composable
fun DarkTheme(
    darkThemeFlow: StateFlow<Boolean>,
    changeTheme: (theme: Boolean) -> Unit
) {
    val darkTheme = darkThemeFlow.collectAsState().value

    IconButton(onClick = {
        changeTheme(!darkTheme)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sleep),
            contentDescription = null
        )
    }
}
