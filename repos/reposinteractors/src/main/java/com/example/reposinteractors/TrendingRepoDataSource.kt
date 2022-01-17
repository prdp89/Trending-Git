package com.example.reposinteractors

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.domain.DEFAULT_REPO_VALUE
import com.example.core.domain.PAGE_SIZE
import com.example.core.domain.REPO_CONST
import com.example.core.domain.TrendingRepository
import com.example.core.dto.TrendingRepoDTO
import com.example.core.dto.toTrendingRepo
import com.example.internetdetector.core.isOnline
import com.example.paging.NoConnectionException
import com.example.paging.canNotLoadMoreContent
import com.example.trending.repo.TrendingRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingRepoDataSource @AssistedInject constructor(
    @ApplicationContext private val context: Context,
    @Assisted(REPO_CONST) private val repoName: String,
    private val trendingRepository: TrendingRepository
    ) : PagingSource<Int, TrendingRepo>() {

    var canLoadMore = true

    @AssistedFactory
    interface TrendingRepoDataSourceFactory {
        fun create(
            @Assisted(REPO_CONST) repoName: String
        ): TrendingRepoDataSource
    }

    override fun getRefreshKey(state: PagingState<Int, TrendingRepo>): Int?  = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingRepo> {
        val page = params.key ?: 1

        return if (context.isOnline) {
            try {
                val it = withContext(Dispatchers.IO) {
                    trendingRepository.getTrendingRepos(page, repoName.ifEmpty { DEFAULT_REPO_VALUE })
                }
                if (it == null) {
                    canNotLoadMoreContent()
                } else {
                    tryToLoadBooks(page, it)
                }
            } catch (t: Throwable) {
                return LoadResult.Error(t)
            }
        } else {
            return LoadResult.Error(NoConnectionException())
        }
    }

    private fun tryToLoadBooks(page: Int, it: List<TrendingRepoDTO>): LoadResult.Page<Int, TrendingRepo> {
        return if (canLoadMore) {
            loadBooks(it, page)
        } else {
            canNotLoadMoreContent()
        }
    }

    private fun loadBooks(list: List<TrendingRepoDTO>?, page: Int): LoadResult.Page<Int, TrendingRepo> {

        val repoDTO = list?.map { it.toTrendingRepo() }

        list?.let {
            if (it.size < PAGE_SIZE.toInt()) {
                //cant load more
                canLoadMore = false
            }
        }

        return if (repoDTO.isNullOrEmpty()) {
            canNotLoadMoreContent()
        } else {
            val prevKey = if (repoDTO.isEmpty()) if (page == 1) null else page - 1 else null
            val nextKey = if (repoDTO.count() == 0) null else page.plus(1)
            LoadResult.Page(repoDTO, prevKey, nextKey)
        }
    }
}