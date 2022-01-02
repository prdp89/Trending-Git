package com.example.reposui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.components.SearchUi
import com.example.paging.PagingUIProviderViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReposScreen() {
    val viewModel: RepoListViewModel = hiltViewModel()
    val pagingUIUIProvider: PagingUIProviderViewModel = hiltViewModel()
    //val state = viewModel.state.value
    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()

    Scaffold(
        content = {
            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchUi(textState, stringResource(id = R.string.search_repos))

                Text(text = pagingItems.itemCount.toString(), color = Color.Blue)

                LazyColumn() {
                        /*items(pagingItems.) {
                            RepoListItem(repoItem = item) {
                                //on click..
                            }
                        }*/
                    }
            }
        }
    )
}