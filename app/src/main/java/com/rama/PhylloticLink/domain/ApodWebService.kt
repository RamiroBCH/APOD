package com.rama.PhylloticLink.domain

import com.rama.PhylloticLink.data.model.ItemApod
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodWebService {
    @GET("apod")
    suspend fun getApod(@Query("api_key") api_key:String):ItemApod
}