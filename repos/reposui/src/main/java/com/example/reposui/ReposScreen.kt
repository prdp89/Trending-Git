package com.example.reposui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.settings.SettingsViewModel
import com.example.trending.TrendingRepo
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReposScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()

    val itemList = listOf(
        TrendingRepo(
            1,
            "Fresh Vegges and Greens",
            "Very awesome list item has very awesome subtitle. This is bit long",
            "R.drawable.food1"
        ),
        TrendingRepo(
            2,
            "Best blue berries",
            "Very awesome list item has very awesome subtitle. This is bit long",
            "R.drawable.food2"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Repos Screen") },
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
                LazyColumn {
                    items(itemList) {
                        item ->
                            RepoListItem(repoItem = item) {
                                //on click..
                            }
                    }
                }
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