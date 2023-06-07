package com.example.imdb.search.domain

import com.example.imdb.model.MovieResult
import com.example.imdb.search.data.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {

    suspend operator fun invoke(apiKey: String): List<MovieResult>{
        return repository.getTopRatedMovies(apiKey)
    }
}