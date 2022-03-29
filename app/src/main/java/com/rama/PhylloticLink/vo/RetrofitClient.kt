package com.rama.PhylloticLink.vo

import com.google.gson.GsonBuilder
import com.rama.PhylloticLink.domain.ApodWebService
import com.rama.PhylloticLink.domain.MarsWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(MarsWebService::class.java)
    }
    val apodwebservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApodWebService::class.java)
    }
}