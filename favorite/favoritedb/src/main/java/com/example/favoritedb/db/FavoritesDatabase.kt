package com.example.favoritedb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.domain.ParameterizedSingleton
import com.example.core.entity.FavoriteRepoEntity

const val FAVORITES_DB_NAME = "favorites-db"


@Database(entities = [FavoriteRepoEntity::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun dao(): FavoritesDAO

    companion object : ParameterizedSingleton<FavoritesDatabase, Context>({
        Room.databaseBuilder(it, FavoritesDatabase::class.java, FAVORITES_DB_NAME)
            .build()
    })
}