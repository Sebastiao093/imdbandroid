package com.example.imdb.core.dependencyinjection

import com.example.imdb.search.data.network.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val baseUrl = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()

    }

    @Singleton
    @Provides
    fun provideGetTopRatedMovies(retrofit: Retrofit): MoviesApi{
        return retrofit.create(MoviesApi::class.java)
    }



}