package com.example.imdb.search.data.network

import com.example.imdb.model.MovieInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MovieInfo>

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Response<MovieInfo>

}