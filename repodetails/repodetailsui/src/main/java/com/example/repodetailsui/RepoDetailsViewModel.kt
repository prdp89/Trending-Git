package com.example.repodetailsui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.core.domain.DataState
import com.example.core.entity.FavoriteRepoEntity
import com.example.favoritedb.db.FavoritesDAO
import com.example.navigator.Navigator
import com.example.navigator.destinations.RepoDetailsDestination.REPO_AUTHOR_PARAM
import com.example.navigator.destinations.RepoDetailsDestination.REPO_ID_PARAM
import com.example.navigator.destinations.RepoDetailsDestination.REPO_NAME_PARAM
import com.example.paging.data.PagingDataSourceHandle
import com.example.repodetailinteractor.GetRepoContributorsData
import com.example.repodetailinteractor.GetTrendingRepoData
import com.example.trending.repo.TrendingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getTrendingRepo : GetTrendingRepoData,
    private val favoriteDao : FavoritesDAO,
    private val getRepoContributorsData: GetRepoContributorsData,
    override val savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
) : ViewModel(), PagingDataSourceHandle, Navigator by navigator {

    val name
        get() = savedStateHandle.get<String>(REPO_NAME_PARAM)
            ?: throw IllegalStateException("Parameter Name must not be null!")

    private val id
        get() = savedStateHandle.get<Int>(REPO_ID_PARAM)
            ?: throw IllegalStateException("Parameter ID must not be null!")

    private val author
        get() = savedStateHandle.get<String>(REPO_AUTHOR_PARAM)
            ?: throw IllegalStateException("Parameter Author must not be null!")

    private val _state = mutableStateOf(RepoDetailsState())
    val state: State<RepoDetailsState> = _state

    /*private val _contributorsData = mutableStateOf(RepoDetailsState())
    val contributors: State<RepoDetailsState> = _contributorsData*/

    private val _contributorsData: MutableStateFlow<RepoDetailsState> = MutableStateFlow(RepoDetailsState())
    val contributors = _contributorsData.asStateFlow()

    private val favoriteRepoData: MutableStateFlow<FavoriteRepoEntity?> = MutableStateFlow(null)
    val favoriteRepo = favoriteRepoData.asStateFlow()

    init {
        getRepo()
        getFavoriteStatus()
    }

    private fun getFavoriteStatus() {
        viewModelScope.launch {
            favoriteDao.getFavoriteById(id).collect {
                favoriteRepoData.value = it
            }
        }
    }

    private fun getRepo() {
        getTrendingRepo(name, author).onEach { result ->
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

    fun addToFavorites(data: FavoriteRepoEntity) {
        viewModelScope.launch {
            favoriteDao.insertIntoFavorites(data)
        }
    }
}