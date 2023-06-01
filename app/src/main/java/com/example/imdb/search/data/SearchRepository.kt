package com.example.imdb.search.data

import com.example.imdb.model.MovieResult
import com.example.imdb.search.data.network.SearchService

class SearchRepository {
    private val api = SearchService()

    suspend fun getTopRatedMovies(apiKey: String): List<MovieResult>{
        return api.getTopRatedMovies(apiKey)
    }
}