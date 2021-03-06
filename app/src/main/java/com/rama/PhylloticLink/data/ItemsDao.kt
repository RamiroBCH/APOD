package com.rama.PhylloticLink.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rama.PhylloticLink.domain.DModels

@Dao
interface ItemsDao {

    @Query("SELECT * FROM MARS WHERE sol = :sol")
    suspend fun getAllMarsPhotos(sol:Int): List<MarsEntities>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarsToRoom(mars: List<MarsEntities>)

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
