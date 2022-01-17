
package com.example.favoriteui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.components.ConfirmationDialog
import com.example.components.ErrorMessage
import com.example.core.entity.FavoriteRepoEntity
import com.example.favoritedb.FavoritesViewModel
import com.example.paging.PagingUIProviderViewModel
import com.example.paging.appendState
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.conflate


@ExperimentalAnimationApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen() {

    val viewModel: FavoritesViewModel = hiltViewModel()
    val pagingUIProviderViewModel: PagingUIProviderViewModel = hiltViewModel()
    val pagingItems = viewModel.favoritesData.collectAsLazyPagingItems()
    var progressVisibility by remember { mutableStateOf(false) }

    val longClickedBook = remember { mutableStateOf<FavoriteRepoEntity?>(null) }

    longClickedBook.value?.apply {
        DeleteFavRepo(it = this,
            onConfirm = { viewModel.removeFromFavorites(it) },
            onDismiss = { longClickedBook.value = null })
    }

    progressVisibility =
        pagingUIProviderViewModel.progressBarVisibility(pagingItems)

    val isDatabaseEmpty = viewModel.count.conflate()
        .collectAsState(1).value == 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.fav_repos)) },
                elevation = 8.dp,
                actions = {

                })
        }, content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background))
            {
                AnimatedVisibility(visible = progressVisibility, modifier = Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator()
                }

                if (isDatabaseEmpty && !progressVisibility) {
                    ErrorMessage(text = R.string.no_favorites_repos)
                } else {
                    pagingUIProviderViewModel.onPaginationReachedError(
                        pagingItems.appendState,
                        R.string.no_more_favorite_repos
                    )
                }

                val swipeToRefreshState = rememberSwipeRefreshState(isRefreshing = false)
                SwipeRefresh(
                    state = swipeToRefreshState, onRefresh = {
                        swipeToRefreshState.isRefreshing = true
                        pagingItems.refresh()
                        swipeToRefreshState.isRefreshing = false
                    },
                    modifier = Modifier.fillMaxSize()
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                            .padding(top = 8.dp),
                        contentPadding = rememberInsetsPaddingValues(
                            insets = LocalWindowInsets.current.navigationBars,
                            applyTop = false,
                            additionalBottom = 16.dp
                        )
                    ) {
                        items(pagingItems) { fav ->
                            fav?.let {
                                FavoriteListItem(it, onLong = {
                                    longClickedBook.value = it
                                })
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DeleteFavRepo(
    it: FavoriteRepoEntity = FavoriteRepoEntity(),
    onDismiss: () -> Unit = {}, onConfirm: (id: Int) -> Unit = {}
) {
    ConfirmationDialog(
        title = stringResource(
            R.string.remove_repo_from_favs,
            it.name.toString()
        ), onDismiss = onDismiss, onConfirm = {
            onConfirm(it.id)
        }, confirmText = stringResource(R.string.remove)
    )
}


