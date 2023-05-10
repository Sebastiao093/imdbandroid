package com.example.imdb.model

import androidx.annotation.DrawableRes

data class Movie(
    var name: String,
    var year: Int,
    var actors: String,
    @DrawableRes var photo: Int
    ) {

}
