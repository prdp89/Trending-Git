package com.example.repodetailsui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.components.stateWhenStarted
import com.example.core.entity.FavoriteRepoEntity
import com.example.style.Shapes

@ExperimentalCoilApi
@Composable
fun ReposDetailsScreen() {
    val viewModel: RepoDetailsViewModel = hiltViewModel()
    val state = viewModel.state.value
    val favoritesBook by stateWhenStarted(viewModel.favoriteRepo, null)

    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            lazyListState,
        ) {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberImagePainter(state.repo?.avatar),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .graphicsLayer {
                                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                translationY = scrolledY * 0.5f
                                previousOffset = lazyListState.firstVisibleItemScrollOffset
                            }
                            .height(240.dp)
                            .fillMaxWidth()
                    )

                    AddToFavorites(modifier = Modifier.align(Alignment.TopEnd), favoritesBook != null) {
                        viewModel.addToFavorites(FavoriteRepoEntity(state.repo?.id,
                        state.repo?.avatar,
                        state.repo?.name,
                        state.repo?.url).also { it.description = state.repo?.description }
                        )
                    }
                }
            }

            item {
                ContributerUi(viewModel = viewModel)
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
@Preview
fun AddToFavorites(
    modifier: Modifier = Modifier,
    isInFavorites: Boolean = false,
    onClicked: () -> Unit = {}
) {
    val favoritesIndicator =
        if (isInFavorites) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Button(
        onClick = onClicked,
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
        modifier = modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = favoritesIndicator,
            contentDescription = null,
        )
    }
}