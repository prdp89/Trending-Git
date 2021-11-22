package com.example.reposui

import android.widget.SearchView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trending.repo.TrendingRepo
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.SearchUi


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReposScreen() {
    val viewModel: RepoListViewModel = hiltViewModel()

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
        content = {
            Column {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchUi(textState, stringResource(id = R.string.search_repos))
                    LazyColumn() {
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