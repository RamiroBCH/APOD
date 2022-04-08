package com.rama.PhylloticLink.di

import androidx.core.app.AppComponentFactory
import com.google.gson.GsonBuilder
import com.rama.PhylloticLink.domain.ApodWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleTwo {
    @Singleton
    @Provides
    fun provideApod(): ApodWebService {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApodWebService::class.java)
    }
}
