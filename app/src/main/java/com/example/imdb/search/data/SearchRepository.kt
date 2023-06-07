package com.example.imdb.search.data

import com.example.imdb.model.MovieResult
import com.example.imdb.search.data.network.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SearchService){

    suspend fun getTopRatedMovies(apiKey: String): List<MovieResult>{
        return api.getTopRatedMovies(apiKey)
    }
}