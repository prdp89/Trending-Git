package com.example.repodetailsui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.core.domain.DataState
import com.example.navigator.destinations.RepoDetailsDestination.REPO_NAME_PARAM
import com.example.paging.data.PagingDataSourceHandle
import com.example.repodetailinteractor.GetRepoContributorsData
import com.example.repodetailinteractor.GetTrendingRepoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getTrendingRepo : GetTrendingRepoData,
    private val getRepoContributorsData: GetRepoContributorsData,
    override val savedStateHandle: SavedStateHandle,
) : ViewModel(), PagingDataSourceHandle {

    val name
        get() = savedStateHandle.get<String>(REPO_NAME_PARAM)
            ?: throw IllegalStateException("Parameter Name must not be null!")

    private val _state = mutableStateOf(RepoDetailsState())
    val state: State<RepoDetailsState> = _state

    private val _contributorsData = mutableStateOf(RepoDetailsState())
    val contributors: State<RepoDetailsState> = _contributorsData

    init {
        getRepo()
    }

    private fun getRepo() {
        getTrendingRepo(name).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _state.value = RepoDetailsState(repo = result.data)
                }
                is DataState.Failure -> {
                    _state.value = RepoDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is DataState.Loading -> {
                    _state.value = RepoDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRepoContributors(repoName: String) {
        getRepoContributorsData(repoName).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _contributorsData.value = RepoDetailsState(contributors = result.data?: emptyList())
                }
                is DataState.Failure -> {
                    _contributorsData.value = RepoDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is DataState.Loading -> {
                    _contributorsData.value = RepoDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}