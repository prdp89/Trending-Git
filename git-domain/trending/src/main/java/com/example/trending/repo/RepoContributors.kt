package com.example.trending.repo

import com.example.base.TrendingData


class RepoContributors (override val id: Int?,
                        override val avatar: String?,
                        override val name: String?,
                        override var url: String?) : TrendingData {
}