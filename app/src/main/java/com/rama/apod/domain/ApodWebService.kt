package com.rama.apod.domain

import com.rama.apod.data.model.ItemApod
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodWebService {
    @GET("apod")
    suspend fun getApod(@Query("api_key") api_key:String):ItemApod
}