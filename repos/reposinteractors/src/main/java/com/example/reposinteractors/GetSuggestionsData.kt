package com.example.reposinteractors

import androidx.compose.runtime.Immutable
import javax.inject.Inject

class GetSuggestionsData @Inject constructor() {

    private val suggestions:List<SearchSuggestionGroup> = searchSuggestions
    fun getSuggestionsList() : List<SearchSuggestionGroup> {
        return suggestions
    }
}

@Immutable
data class SearchSuggestionGroup(
    val id: Long,
    val name: String,
    val suggestions: List<String>
)

val searchSuggestions = listOf(
    SearchSuggestionGroup(
        id = 0L,
        name = "Popular searches",
        suggestions = listOf(
            "Google",
            "Square",
            "Microsoft",
            "Walmart"
        )
    )
)