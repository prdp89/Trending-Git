package com.example.repodetailsui

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigator.Navigator
import com.example.navigator.destinations.RepoDetailsDestination.REPO_ID_PARAM
import com.example.paging.data.PagingDataSourceHandle
import com.example.paging.stateHandleDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(override val savedStateHandle: SavedStateHandle,
) : ViewModel(), PagingDataSourceHandle {

    val id
        get() = savedStateHandle.get<Int>(REPO_ID_PARAM)
            ?: throw IllegalStateException("Parameter ID must not be null!")
}