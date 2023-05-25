package com.example.imdb.search

import android.content.res.Resources
import com.example.imdb.model.MovieInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MovieInfo>

}