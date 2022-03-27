package com.rama.apod.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rama.apod.data.model.Photo

@Dao
interface ItemsDao {
    @Query("SELECT * FROM MARS")
    suspend fun getAllMarsPhotos(): List<MarsEntities>

    @Query("SELECT * FROM APOD")
    suspend fun getAllApod(): List<ApodEntities>

    @Query("SELECT * FROM FAV")
    suspend fun getAllFavorites(): List<FavItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotoMars(mars: MarsEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addApod(apod: ApodEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePhoto(photo: FavItems)
}