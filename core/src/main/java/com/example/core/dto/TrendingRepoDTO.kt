package com.example.core.dto

import com.example.trending.repo.TrendingRepo
import com.google.gson.annotations.SerializedName

data class TrendingRepoDTO (
    val id: Int,
    val name: String,
    val description: String,
    val forks : Int,
    @SerializedName("stargazers_count")
    val stars: Int,
    val language: String,

    @SerializedName("owner")
    val owner: Owner
        ) {

    data class Owner(
        @SerializedName("avatar_url")
        val avatar: String,

        val url : String,

        //author
        val login : String
    )
}

fun TrendingRepoDTO.toTrendingRepo() : TrendingRepo {
    return TrendingRepo(id = id,
                        name = name,
                        avatar = owner.avatar,
                        url = owner.url).
                        also {
                                it.author = owner.login
                                it.description = description
                                it.forks = forks
                                it.stars = stars
                            }
}