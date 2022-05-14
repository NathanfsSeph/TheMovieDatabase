package com.nathan.themoviedatabase.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathan.themoviedatabase.data.APIService
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.data.response.MovieBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    val moviesLiveData : MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovies()  {
        APIService.service.getMovies().enqueue(object: Callback<MovieBodyResponse>{
            override fun onResponse( call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>) {
                if (response.isSuccessful){
                    val movies : MutableList<Movie> = mutableListOf()

                    response.body()?.let { movieBodyResponse ->
                        for (result in movieBodyResponse.results) {
                            val movie = Movie(
                                poster_path = result.poster_path,
                                overview = result.overview,
                                release_date = result.release_date,
                                genre_ids = result.genre_ids,
                                original_language = result.original_language,
                                title = result.title
                            )
                            movies.add(movie)
                        }
                    }

                    moviesLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

            }

        })
    }

}