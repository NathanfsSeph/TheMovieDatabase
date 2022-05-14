package com.nathan.themoviedatabase.data.model

data class Movie(
    val poster_path : String? = "",
    val overview : String? = "",
    val release_date : String? = "",
    val genre_ids : List<Int>? = listOf(),
    val original_language : String? = "",
    val title : String? = ""
)