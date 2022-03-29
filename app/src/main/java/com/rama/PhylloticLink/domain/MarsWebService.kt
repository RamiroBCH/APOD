package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.model.ItemMarsPhotos
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsWebService {
    @GET("photos")
    suspend fun getMarsPhotos(@Query("sol") sol:Int,@Query("api_key") api_key:String) : ItemMarsPhotos
}