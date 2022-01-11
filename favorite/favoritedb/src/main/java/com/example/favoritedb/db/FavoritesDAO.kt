package com.example.favoritedb.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.core.entity.FavoriteRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDAO {

    @Query("select * from favoriteRepos")
    fun getAllFavorites(): PagingSource<Int, FavoriteRepoEntity>

    @Query("select count(*) from favoriteRepos")
    fun favoriteItemsCount(): Flow<Int>

    @Delete
    suspend fun deleteFromFavorites(favoriteBook: FavoriteRepoEntity)

    @Query("delete from favoriteRepos where id =:favID")
    suspend fun deleteFromFavoritesByID(favID: Int)

    @Insert(onConflict = REPLACE)
    suspend fun insertIntoFavorites(favoriteBook: FavoriteRepoEntity)

    @Query("select * from favoriteRepos where id =:bookID limit 1")
    fun getFavoriteById(bookID: Int): Flow<FavoriteRepoEntity?>
}