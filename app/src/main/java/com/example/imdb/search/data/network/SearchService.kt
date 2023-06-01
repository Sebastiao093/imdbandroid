package com.example.imdb.search.data.network

import android.util.Log
import com.example.imdb.core.network.RetrofitHelper
import com.example.imdb.model.MovieResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchService {

    private val moviesApi = RetrofitHelper.getInstance().create(MoviesApi::class.java)

    suspend fun getTopRatedMovies(apiKey: String): List<MovieResult> =
        withContext(Dispatchers.IO + coroutineExceptionHandler) {
            val result = moviesApi.getTopRatedMovies(apiKey)
            if (result != null)
                Log.d("respuesta aqui: ", result.body().toString())
            return@withContext result.body()!!.results
            // Checking the results
        }

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }



}