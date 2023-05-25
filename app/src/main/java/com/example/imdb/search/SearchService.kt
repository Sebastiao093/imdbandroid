package com.example.imdb.search

import android.util.Log
import com.example.imdb.core.network.RetrofitHelper
import com.example.imdb.model.MovieInfo
import com.example.imdb.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class SearchService {

    val moviesApi = RetrofitHelper.getInstance().create(MoviesApi::class.java)

    suspend fun getTopRatedMovies(apiKey: String): List<MovieResult> =
        withContext(Dispatchers.IO) {
            val result = moviesApi.getTopRatedMovies(apiKey)
            if (result != null)
                Log.d("respuesta aqui: ", result.body().toString())
            return@withContext result.body()!!.results
            // Checking the results
        }



}