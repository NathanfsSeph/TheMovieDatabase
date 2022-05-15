package com.nathan.themoviedatabase.data

import com.nathan.themoviedatabase.data.response.MovieBodyResponse
import com.nathan.themoviedatabase.data.response.MovieResultsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbServices {

    @GET("movie/now_playing?api_key=f321a808e68611f41312aa8408531476")
    fun getMovies(
        @Query("page") page : Int = 1
    ): Call<MovieBodyResponse>

    // https://developers.themoviedb.org/3/search/search-movies
    // movie?api_key=f321a808e68611f41312aa8408531476&query=Jack%20Reacher
    @GET("search/movie/?api_key=f321a808e68611f41312aa8408531476")
    fun getSpecificMovie(
        @Query("query") query : String,
        @Query("page") page : Int = 1
    ): Call<MovieBodyResponse>


}