package com.example.imdb.model

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    val page: Long,
    val results: List<MovieResult>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long

)