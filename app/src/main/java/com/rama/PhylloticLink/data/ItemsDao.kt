package com.rama.PhylloticLink.data

import androidx.room.*

@Dao
interface ItemsDao {

    @Query("SELECT * FROM MARS")
    suspend fun getAllMarsPhotos(): List<MarsEntities>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarsToRoom(mars: MarsEntities)

    @Query("SELECT * FROM APOD")
    suspend fun getAllApod(): ApodEntities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApodToRoom(apod: ApodEntities)

    @Query("SELECT * FROM FAV")
    suspend fun getAllFavorites(): List<NormalizedItem>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePhoto(photo: NormalizedItem)
    @Delete
    suspend fun deleteFromFavorites(normalizedItem: NormalizedItem)
}