package com.example.core.dto

import com.example.trending.repo.RepoContributors
import com.google.gson.annotations.SerializedName

data class RepoContributorsDTO (
    val id: Int,
    @SerializedName("avatar_url")
    val avatar: String,

    val url : String,

    //author
    val login : String
    )

fun RepoContributorsDTO.toRepoContributors() : RepoContributors {
    return RepoContributors(id = id,
                        name = login,
                        avatar = avatar,
                        url = url)
}