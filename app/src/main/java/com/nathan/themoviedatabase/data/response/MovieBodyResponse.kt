package com.nathan.themoviedatabase.data.response

import com.google.gson.annotations.SerializedName


data class MovieBodyResponse(
    @SerializedName( "results")
    val results : List<MovieResultsResponse>
    )