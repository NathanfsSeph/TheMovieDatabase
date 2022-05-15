package com.nathan.themoviedatabase.data.response

import com.google.gson.annotations.SerializedName

data class MovieResultsResponse (
    @SerializedName("poster_path")
    val poster_path : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("release_date")
    val release_date : String,
    @SerializedName("genre_ids")
    val genre_ids : List<Int>,
    @SerializedName("original_language")
    val original_language : String,
    @SerializedName("title")
    val title : String
    )