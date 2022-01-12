package com.example.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.base.TrendingData

@Entity(tableName = "favoriteRepos")
data class FavoriteRepoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int?,

    @ColumnInfo(name = "avatar")
    override val avatar: String?,

    @ColumnInfo(name = "name")
    override val name: String?,

    @ColumnInfo(name = "url")
    override var url: String?
) : TrendingData {

    @ColumnInfo(name = "description")
    var description: String ? = null
}