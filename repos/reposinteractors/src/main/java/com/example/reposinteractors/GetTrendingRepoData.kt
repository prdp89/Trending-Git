package com.example.reposinteractors

import com.example.trending.repo.TrendingRepo
import javax.inject.Inject
import javax.inject.Singleton

public class GetTrendingRepoData @Inject constructor () {

    fun execute() : List<TrendingRepo> = List<TrendingRepo>(0) {
        return@List TrendingRepo(0 , " ", "", "")
    }
}