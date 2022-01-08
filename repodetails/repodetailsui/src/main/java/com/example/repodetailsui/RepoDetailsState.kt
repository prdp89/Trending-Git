package com.example.repodetailsui

import com.example.trending.repo.TrendingRepo

data class RepoDetailsState (
    val isLoading: Boolean = false,
    val coins: TrendingRepo? = null,
    val error: String = ""
)