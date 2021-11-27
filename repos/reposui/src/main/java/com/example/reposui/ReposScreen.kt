package com.example.reposui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.SearchUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReposScreen() {
    val viewModel: RepoListViewModel = hiltViewModel()
    val state = viewModel.state.value

    Scaffold(
        content = {
            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchUi(textState, stringResource(id = R.string.search_repos))
                    LazyColumn() {
                        items(state.coins) { item ->
                            RepoListItem(repoItem = item) {
                                //on click..
                            }
                        }
                    }
            }
        }
    )
}