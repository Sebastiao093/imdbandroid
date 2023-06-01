package com.example.imdb.splash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    fun launchedEffect(){
        viewModelScope.launch(Dispatchers.IO) {  }
    }
}