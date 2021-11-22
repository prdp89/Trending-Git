package com.example.reposui

import androidx.lifecycle.ViewModel
import com.example.reposinteractors.GetTrendingRepoData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val getRepo : GetTrendingRepoData) : ViewModel() {

   // @Inject lateinit var getRepo : GetTrendingRepoData

}