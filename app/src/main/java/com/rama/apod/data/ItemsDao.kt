package com.rama.apod.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rama.apod.data.model.Photo

@Dao
interface ItemsDao {
    @Query("SELECT * FROM FAV")
    suspend fun getAllFavorites(): List<FavItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePhoto(photo: FavItems)
}