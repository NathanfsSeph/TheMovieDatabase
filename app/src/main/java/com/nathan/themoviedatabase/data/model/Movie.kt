package com.nathan.themoviedatabase.data.model

import java.io.Serializable


data class Movie(
    val poster_path : String? = "",
    val overview : String? = "",
    val release_date : String? = "",
    val genres : List<String>? = listOf(),
    val original_language : String? = "",
    val title : String? = ""
) : Serializable