package com.example.imdb.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.model.MovieResult
import com.example.imdb.search.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase):ViewModel() {

    private val _apiKey: String = "c5c47722a4adcc77f6e84f28a48b857a"

    private val _movieList = MutableLiveData<List<MovieResult>>()
    val movieList: LiveData<List<MovieResult>> = _movieList

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String> = _searchText

    init {
        _movieList.value = ArrayList()
        getTopRatedMovies()
    }

    fun searchTextChanged(searchText: String){
        _searchText.value = searchText
    }

    fun getTopRatedMovies() =
        viewModelScope.launch(Dispatchers.IO) {
            val result = doInBackground()
            withContext(Dispatchers.Main){
                _movieList.value = result
            }
        }


    private suspend fun doInBackground(): List<MovieResult> = withContext(Dispatchers.IO) { // to run code in Background Thread
        return@withContext searchUseCase(_apiKey)
    }

}