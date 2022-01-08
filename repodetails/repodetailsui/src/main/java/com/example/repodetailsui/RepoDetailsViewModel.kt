package com.example.repodetailsui

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DataState
import com.example.navigator.Navigator
import com.example.navigator.destinations.RepoDetailsDestination.REPO_NAME_PARAM
import com.example.paging.data.PagingDataSourceHandle
import com.example.paging.stateHandleDelegate
import com.example.repodetailinteractor.GetTrendingRepoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getTrendingRepo : GetTrendingRepoData,
    override val savedStateHandle: SavedStateHandle,
) : ViewModel(), PagingDataSourceHandle {

    val name
        get() = savedStateHandle.get<String>(REPO_NAME_PARAM)
            ?: throw IllegalStateException("Parameter Name must not be null!")

    private val _state = mutableStateOf(RepoDetailsState())
    val state: State<RepoDetailsState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getTrendingRepo(name).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _state.value = RepoDetailsState(coins = result.data)
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
}