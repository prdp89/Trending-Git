package com.example.reposui

import com.example.trending.repo.TrendingRepo

data class RepoListState (val isLoading: Boolean = false,
                          val coins: List<TrendingRepo> = emptyList(),
                          val error: String = "")