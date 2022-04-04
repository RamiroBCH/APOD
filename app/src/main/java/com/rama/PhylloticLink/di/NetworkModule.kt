package com.rama.PhylloticLink.di

import com.google.gson.GsonBuilder
import com.rama.PhylloticLink.domain.ApodWebService
import com.rama.PhylloticLink.domain.MarsWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    @Singleton
    @Provides
    fun provideMarsWebService(retrofit: Retrofit): MarsWebService{
        return retrofit.create(MarsWebService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitApod(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideApodWebService(retrofit: Retrofit): ApodWebService {
        return retrofit.create(ApodWebService::class.java)
    }
}