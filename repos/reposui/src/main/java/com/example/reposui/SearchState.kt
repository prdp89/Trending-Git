package com.example.reposui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.compose.LazyPagingItems
import com.example.trending.repo.TrendingRepo

class SearchState (query: TextFieldValue,
                   focused: Boolean,
                   searching: Boolean,
                   searchResults: LazyPagingItems<TrendingRepo>?) {

    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searchResults  = (searchResults)

    val searchDisplay: SearchDisplay
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplay.Categories
            focused && query.text.isEmpty() -> SearchDisplay.Suggestions
            //searchResults == null || searchResults?.itemCount == 0 -> SearchDisplay.NoResults
            else -> {
                SearchDisplay.Results
            }
        }
}

enum class SearchDisplay {
    Suggestions, Categories, Results
}