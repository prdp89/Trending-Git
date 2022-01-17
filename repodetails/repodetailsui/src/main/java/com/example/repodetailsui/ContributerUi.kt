package com.example.repodetailsui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.components.stateWhenStarted
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.flow.firstOrNull

private val MinTitleOffset = 56.dp
private val GradientScroll = 180.dp

@ExperimentalCoilApi
@Composable
fun ContributerUi(viewModel: RepoDetailsViewModel, scrollState: ScrollState) {

    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(GradientScroll))
            /*
    https://www.section.io/engineering-education/side-effects-and-effects-handling-in-jetpack-compose/
    rememberCoroutineScope allows us to initiate coroutines from any composables or callbacks.
    This can be done without having to worry about the coroutine’s lifespan.
     */
            val scope = rememberCoroutineScope()

            //https://stackoverflow.com/questions/66474049/using-remembercoroutinescope-vs-launchedeffect
            //LaunchedEffect should be used when you want that some action must be taken when your composable is first launched.
            //For example, when you want to request some data from your ViewModel or run some sort of animation...
            /*LaunchedEffect (scope) {
        viewModel.getRepoContributors(viewModel.name)
    }*/

            //val state = viewModel.state.value

            val lazyListState = rememberLazyListState()

            /*val produceState =  produceState(initialValue = viewModel.state ) {
        viewModel.getRepoContributors(viewModel.name)
        value = viewModel.contributors
    }*/

            //To prevent reloading of Contributers on BottomSheet switching..
            LaunchedEffect(scope) {
                if (null != viewModel.contributors.firstOrNull { it -> it.contributors.isEmpty() })
                    viewModel.getRepoContributors(viewModel.name)
            }

            val contributors by stateWhenStarted(viewModel.contributors, RepoDetailsState())

            Box {
                if (contributors.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                Column {
                    Spacer(Modifier.height(128.dp))
                    Spacer(Modifier.height(16.dp))

                    ListItemDivider()

                    DetailsText(modifier = Modifier)

                    if (contributors.contributors.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .heightIn(min = 56.dp)
                                .padding(start = 24.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.contributors_text),
                                style = MaterialTheme.typography.h6,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.Start)
                            )

                            IconButton(
                                onClick = { /* todo */ },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    imageVector = mirroringIcon(
                                        ltrIcon = Icons.Outlined.ArrowForward,
                                        rtlIcon = Icons.Outlined.ArrowBack
                                    ),
                                    tint = MaterialTheme.colors.onSurface,
                                    contentDescription = null
                                )
                            }
                        }

                        LazyRow(
                            Modifier.padding(end = 16.dp, top = 8.dp),
                            lazyListState,
                        ) {
                            items(contributors.contributors) { item ->
                                ContributorItem(item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun DetailsText(modifier: Modifier) {

    Column {
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.detail_header),
            style = MaterialTheme.typography.overline,
            modifier = modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(16.dp))
        var seeMore by remember { mutableStateOf(true) }
        Text(
            text = stringResource(R.string.detail_placeholder),
            style = MaterialTheme.typography.body1,
            maxLines = if (seeMore) 5 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(horizontal = 24.dp)
        )
        val textButton = if (seeMore) {
            stringResource(id = R.string.see_more)
        } else {
            stringResource(id = R.string.see_less)
        }
        Text(
            text = textButton,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
            modifier = modifier
                .heightIn(20.dp)
                .fillMaxWidth()
                .padding(top = 15.dp)
                .clickable {
                    seeMore = !seeMore
                }
        )
    }
}

@Composable
fun mirroringIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon
