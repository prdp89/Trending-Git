package com.example.trending.dev

import com.example.trending.TrendingData

class TrendingDev(
    override val id: Int?,
    override val avatar: String?,
    override val name: String?,
    override var url: String?
) : TrendingData {
    var userName: String? =null
    var repo : Repo? = null
}