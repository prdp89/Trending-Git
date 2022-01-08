package com.example.reposui

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigator.Navigator
import com.example.paging.data.PagingDataProvider
import com.example.paging.data.PagingDataSourceHandle
import com.example.paging.stateHandleDelegate
import com.example.reposinteractors.TrendingRepoDataSource
import com.funkymuse.aurora.dispatchers.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val latestRepoDataSourceFactory: TrendingRepoDataSource.TrendingRepoDataSourceFactory
                                            , application: Application
                                            , private val navigator: Navigator
                                            , pagingDataProvider: PagingDataProvider
                                            , @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
                                            override val savedStateHandle: SavedStateHandle,
) : ViewModel(), PagingDataSourceHandle, Navigator by navigator {

    init {
        //onTriggerRepoListEvents()
    }

    private companion object {
        private const val SORT_QUERY_KEY = "sortQuery"
    }

    private var sortQuery by stateHandleDelegate<String>(SORT_QUERY_KEY)

    private val latestRepoDataSource
        get() = latestRepoDataSourceFactory.create(
            sortQuery ?: "")

    val pagingData =
        pagingDataProvider.providePagingData(viewModelScope, ioDispatcher) { latestRepoDataSource }

    fun refresh() {
        latestRepoDataSource.canLoadMore = true
        sortQuery = ""
    }

    //val state: MutableState<RepoListState> = mutableStateOf(RepoListState())

    /*fun onTriggerRepoListEvents() {
        val key = ioDispatcher.key
        getRepo.execute().onEach { result ->
            when(result) {
                is DataState.Loading -> {
                    state.value = RepoListState(isLoading = true)
                }
                is DataState.Success -> {

                    //state.value = RepoListState(coins = result.data?: emptyList())
                }
                is DataState.Failure -> {
                    state.value = RepoListState(error = "")
                }
            }
        }.launchIn(viewModelScope)
    }*/
}