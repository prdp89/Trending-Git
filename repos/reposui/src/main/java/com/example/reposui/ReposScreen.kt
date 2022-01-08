package com.example.reposui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.components.ErrorMessage
import com.example.components.ErrorWithRetry
import com.example.components.SearchUi
import com.example.navigator.destinations.RepoDetailsDestination
import com.example.paging.PagingUIProviderViewModel
import com.example.paging.appendState

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun ReposScreen() {
    val viewModel: RepoListViewModel = hiltViewModel()
    val pagingUIUIProvider: PagingUIProviderViewModel = hiltViewModel()
    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()
    var progressVisibility by remember {mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    progressVisibility =
        pagingUIUIProvider.progressBarVisibility(pagingItems)

    pagingUIUIProvider.onPaginationReachedError(
        pagingItems.appendState,
        R.string.no_more_repo_to_load
    )

    val retry = {
        viewModel.refresh()
        pagingItems.refresh()
    }

    Scaffold(
        content = {

            pagingUIUIProvider.OnError(
                pagingItems = pagingItems,
                scope = scope,
                noInternetUI = {
                    ErrorMessage(R.string.no_repo_loaded_no_connect)
                },
                errorUI = {
                    ErrorWithRetry(R.string.no_repo_search) {
                        retry()
                    }
                }
            )

            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchUi(textState, stringResource(id = R.string.search_repos))

                AnimatedVisibility(visible = progressVisibility,
                    modifier = Modifier.height(10.dp).width(10.dp)
                        .padding(top = 8.dp)
                        .zIndex(2f)) {
                    CircularProgressIndicator()
                }

                LazyColumn (modifier =  Modifier.fillMaxSize()) {
                    items(pagingItems) { item ->
                        item ?: return@items
                        RepoListItem(repoItem = item) {
                            //on click..
                            viewModel.navigate(RepoDetailsDestination.createRepoDetailsRoute(item.id!!))
                        }
                    }
                }
            }
        }
    )
}