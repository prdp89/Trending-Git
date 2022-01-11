package com.example.favoritedb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoritedb.db.FavoritesDAO
import com.example.paging.data.PagingDataProvider
import com.funkymuse.aurora.dispatchers.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesDAO: FavoritesDAO,
    pagingDataProvider: PagingDataProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val favoritesData =
            pagingDataProvider.providePagingData(viewModelScope, ioDispatcher) { favoritesDAO.getAllFavorites() }

    val count = favoritesDAO.favoriteItemsCount()

    fun removeFromFavorites(id: Int) {
        viewModelScope.launch(ioDispatcher) { favoritesDAO.deleteFromFavoritesByID(id) }
    }
}