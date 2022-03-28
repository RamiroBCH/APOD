package com.rama.apod.data

import androidx.room.*
import com.rama.apod.data.model.Photo

@Dao
interface ItemsDao {

    @Query("SELECT * FROM MARS")
    suspend fun getAllMarsPhotos(): List<MarsEntities>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotoMars(mars: MarsEntities)

    @Query("SELECT * FROM APOD")
    suspend fun getAllApod(): List<ApodEntities>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addApod(apod: ApodEntities)

    @Query("SELECT * FROM FAV")
    suspend fun getAllFavorites(): List<FavItems>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePhoto(photo: FavItems)
    @Delete
    suspend fun deleteFromFavorites(favItems: FavItems)
}