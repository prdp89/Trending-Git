package com.example.reposui

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.DEFAULT_REPO_VALUE
import com.example.navigator.Navigator
import com.example.paging.data.PagingDataProvider
import com.example.paging.data.PagingDataSourceHandle
import com.example.paging.stateHandleDelegate
import com.example.reposinteractors.GetSuggestionsData
import com.example.reposinteractors.TrendingRepoDataSource
import com.funkymuse.aurora.dispatchers.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val latestRepoDataSourceFactory: TrendingRepoDataSource.TrendingRepoDataSourceFactory
                                            , application: Application
                                            , suggestions : GetSuggestionsData
                                            , private val navigator: Navigator
                                            , pagingDataProvider: PagingDataProvider
                                            , @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
                                            override val savedStateHandle: SavedStateHandle,
) : ViewModel(), PagingDataSourceHandle, Navigator by navigator {

    private companion object {
        private const val REPO_QUERY_KEY = "repoQuery"
    }

    private var repoQuery by stateHandleDelegate<String>(REPO_QUERY_KEY)

    private val latestRepoDataSource
        get() = latestRepoDataSourceFactory.create(
            repoQuery ?: "")

    val pagingData =
        pagingDataProvider.providePagingData(viewModelScope, ioDispatcher) { latestRepoDataSource }

    val searchSuggestionsData = suggestions.getSuggestionsList()

    fun refresh(searchRepo: String = DEFAULT_REPO_VALUE) {
        latestRepoDataSource.canLoadMore = true
        repoQuery = searchRepo
    }
}