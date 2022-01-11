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

    @PrimaryKey
    @ColumnInfo(name = "id")
    override val avatar: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    override val name: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    override var url: String?
) : TrendingData {

    @ColumnInfo(name = "description")
    var description: String ? = null
}