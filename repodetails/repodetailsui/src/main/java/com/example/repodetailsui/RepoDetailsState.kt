package com.example.repodetailsui

import com.example.trending.repo.RepoContributors
import com.example.trending.repo.TrendingRepo

data class RepoDetailsState (
    val isLoading: Boolean = false,
    val repo: TrendingRepo? = null,
    val contributors: List<RepoContributors> = emptyList(),
    val error: String = ""
)