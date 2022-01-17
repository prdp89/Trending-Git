package com.example.reposui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.components.ErrorMessage
import com.example.components.ErrorWithRetry
import com.example.components.SearchUi
import com.example.navigator.destinations.RepoDetailsDestination
import com.example.paging.PagingUIProviderViewModel
import com.example.paging.appendState
import com.example.trending.repo.TrendingRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class,
    kotlinx.coroutines.ExperimentalCoroutinesApi::class
)
@Composable
fun ReposScreen() {
    val viewModel: RepoListViewModel = hiltViewModel()
    val pagingUIUIProvider: PagingUIProviderViewModel = hiltViewModel()
    var progressVisibility by remember {mutableStateOf(false) }
    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()

    progressVisibility =
        pagingUIUIProvider.progressBarVisibility(pagingItems)
    val state: SearchState = rememberSearchState(searchResults = pagingItems, searching = progressVisibility)

    pagingUIUIProvider.onPaginationReachedError(
        pagingItems.appendState,
        R.string.no_more_repo_to_load
    )

    Scaffold(
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background))
            {
                AnimatedVisibility(visible = progressVisibility,
                    modifier = Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator()
                }

                Search(state, Modifier.fillMaxSize(), viewModel, pagingItems, pagingUIUIProvider)
            }
        }
    )
}

@Composable
private fun Search(
    state: SearchState,
    modifier: Modifier,
    viewModel: RepoListViewModel,
    pagingItems: LazyPagingItems<TrendingRepo>,
    pagingUIUIProvider: PagingUIProviderViewModel
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(pagingItems) {
        state.searchResults = pagingItems
    }

    Column {
        SearchUi(
            query = state.query,
            onQueryChange = {state.query = it },
            onBackKeyPressed = {
                                    state.focused = it.second
                                    state.query = it.first
                               },
            onSearchBegin = {
                                    pagingItems.refresh()
                                    viewModel.refresh(state.query.text)
            },
            stringResource(id = R.string.search_repos),
            state.focused,
            onSearchFocusChange = {state.focused = it }
        )

        when (state.searchDisplay) {
            SearchDisplay.Suggestions -> SearchSuggestions(
                suggestions = viewModel.searchSuggestionsData) {
                pagingItems.refresh()
                viewModel.refresh(it)
                state.focused = false
                focusManager.clearFocus(true)
            }
            SearchDisplay.Categories -> RepoList(pagingItems, viewModel, modifier, pagingUIUIProvider)
            SearchDisplay.Results -> {
                RepoList(pagingItems = pagingItems, viewModel = viewModel, modifier = modifier, pagingUIUIProvider)
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RepoList(pagingItems: LazyPagingItems<TrendingRepo>, viewModel: RepoListViewModel, modifier: Modifier, pagingUIUIProvider: PagingUIProviderViewModel) {
    val scope = rememberCoroutineScope()

    val retry = {
        viewModel.refresh()
        pagingItems.refresh()
    }

    //TODO:Error UI Not working properly
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
    LazyColumn (modifier =  modifier) {
        items(pagingItems) { item ->
            item ?: return@items
            RepoListItem(repoItem = item) {
                //on click..
                viewModel.navigate(RepoDetailsDestination.createRepoDetailsRoute(item.name!!, item.id!!, item.author!!))
            }
        }
    }
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: LazyPagingItems<TrendingRepo>?= null
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults
        )
    }
}