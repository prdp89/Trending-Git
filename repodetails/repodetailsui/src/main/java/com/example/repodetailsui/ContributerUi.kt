package com.example.repodetailsui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.components.stateWhenStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun ContributerUi(viewModel: RepoDetailsViewModel) {

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
    LaunchedEffect (scope) {
       if(null != viewModel.contributors.firstOrNull { it -> it.contributors.isEmpty() })
           viewModel.getRepoContributors(viewModel.name)
    }

    val contributors by stateWhenStarted(viewModel.contributors, RepoDetailsState())

    Box(modifier = Modifier.fillMaxSize()) {
        if (contributors.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Column {

            Text(
                text = viewModel.name,
                style = typography.subtitle1, modifier = Modifier.padding(16.dp)
            )

            ListItemDivider()

            if(contributors.contributors.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.contributors_text),
                    style = typography.subtitle1, modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )

                LazyRow(
                    Modifier.padding(32.dp),
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

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}