package com.rama.apod.domain

import com.rama.apod.data.model.ItemMarsPhotos
import com.rama.apod.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsWebService {
    @GET("photos")
    suspend fun getMarsPhotos(@Query("sol") sol:String,@Query("api_key") api_key:String) : ItemMarsPhotos
}