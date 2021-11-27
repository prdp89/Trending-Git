package com.example.reposui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.core.domain.DataState
import com.example.reposinteractors.GetTrendingRepoData
import com.example.trending.dev.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val getRepo : GetTrendingRepoData) : ViewModel() {

    init {
        onTriggerRepoListEvents()
    }

    val state: MutableState<RepoListState> = mutableStateOf(RepoListState())

    fun onTriggerRepoListEvents() {
        getRepo.execute().onEach { result ->
            when(result) {
                is DataState.Loading -> {
                    state.value = RepoListState(isLoading = true)
                }
                is DataState.Success -> {
                    state.value = RepoListState(coins = result.data?: emptyList())
                }
                is DataState.Failure -> {
                    state.value = RepoListState(error = "")
                }
            }
        }
    }
}