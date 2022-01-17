package com.example.repodetailsui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.components.BackButton
import com.example.components.stateWhenStarted
import com.example.core.entity.FavoriteRepoEntity
import com.example.style.tornado
import com.google.accompanist.insets.statusBarsPadding

private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp

private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll

private val HzPadding = Modifier.padding(horizontal = 24.dp)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ReposDetailsScreen() {
    val viewModel: RepoDetailsViewModel = hiltViewModel()
    val state = viewModel.state.value
    val favoritesBook by stateWhenStarted(viewModel.favoriteRepo, null)
    val onBackClicked = {
        viewModel.navigateUp()
    }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        TopButtons(onBackClicked =  { onBackClicked() },
            favoritesBook != null,
            state,
            viewModel,
            Modifier)
        ContributerUi(viewModel = viewModel, scroll)
        Title(name = viewModel.name, scrollState = scroll)
        state.repo?.avatar?.let { Image(imageUrl = it, scroll = scroll.value) }

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
    }
}

@Composable
fun TopButtons(onBackClicked: () -> Unit, isInFavorites: Boolean, state: RepoDetailsState, viewModel: RepoDetailsViewModel, modifier: Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            onBackClicked()
        }

        AddToFavorites(modifier = Modifier.align(Alignment.TopEnd), isInFavorites) {
            viewModel.addToFavorites(FavoriteRepoEntity(
                state.repo?.id!!,
                state.repo.avatar,
                state.repo.name,
                state.repo.url
            ).also { it.description = state.repo.description }
            )
        }
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(tornado))
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun Image(
    imageUrl: String,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        RepoDetailImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Title(name: String, scrollState: ScrollState) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() - 100.dp.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scrollState.value).coerceAtLeast(minOffset)
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .graphicsLayer { translationY = offset }
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 24.dp)
        )
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
    IconButton(
        onClick = { onClicked() },
        modifier = modifier)
    {
        Icon(
            imageVector = favoritesIndicator,
            contentDescription = null,
        )
    }
}