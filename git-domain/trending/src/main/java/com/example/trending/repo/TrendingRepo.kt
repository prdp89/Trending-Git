package com.example.trending.repo

import com.example.trending.TrendingData

class TrendingRepo(
    override val id: Int?,
    override val avatar: String?,
    override val name: String?,
    override var url: String?
) : TrendingData {
    var author: String? =null
    var currentPeriodStars: Int ? =null
    var description: String ? = null
    var forks: Int? = null
    var stars: Int? = null

    var builtBy: MutableList<RepoBuiltBy>? = null

    override fun equals(other: Any?): Boolean {
        if (other !is TrendingRepo) return false
        return other.id == id
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (avatar?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }
}