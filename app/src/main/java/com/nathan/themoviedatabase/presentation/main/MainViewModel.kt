package com.nathan.themoviedatabase.presentation.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nathan.themoviedatabase.commons.readAssetsFile
import com.nathan.themoviedatabase.data.APIService
import com.nathan.themoviedatabase.data.model.Genres
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.data.response.MovieBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val context: Context
) : ViewModel() {

    private val _mainScreenState: MutableLiveData<MainScreenState> = MutableLiveData()
    val mainScreenState: LiveData<MainScreenState> get() = _mainScreenState

    private val _searchScreenState: MutableLiveData<SearchScreenState> = MutableLiveData()
    val searchScreenState: LiveData<SearchScreenState> get() = _searchScreenState

    private var allMoviesGenres : Genres

    private var homePage : Int = 0
    private var searchPage : Int = 0

    private lateinit var movieNameSearched : String

    init {
        _mainScreenState.value = MainScreenState()
        _searchScreenState.value = SearchScreenState()

        val gson = Gson()
        val jsonString = context.assets.readAssetsFile("genres.json")
        allMoviesGenres = gson.fromJson(jsonString, Genres::class.java)
    }

    fun getMovies() {
        homePage = 1
        APIService.service.getMovies(page = homePage).enqueue(object : Callback<MovieBodyResponse> {
            override fun onResponse(
                call: Call<MovieBodyResponse>,
                response: Response<MovieBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val movies: MutableList<Movie> = mutableListOf()

                    response.body()?.let { movieBodyResponse ->
                        for (result in movieBodyResponse.results) {
                            val movie = Movie(
                                poster_path = result.poster_path,
                                overview = result.overview,
                                release_date = result.release_date,
                                genres = getMovieGenres(result.genre_ids),
                                original_language = result.original_language,
                                title = result.title
                            )
                            movies.add(movie)
                        }
                    }

                    _mainScreenState.value = mainScreenState.value?.copy(
                        movies = movies
                    )
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

            }

        })
    }

    private fun getMovieGenres(genresIds: List<Int>): List<String> {

        val moviesGenres : MutableList<String> = mutableListOf()

        genresIds.forEach{ movieGenreId ->

            val genre = allMoviesGenres.genres.find { it.id == movieGenreId }

            genre?.let {
                moviesGenres.add(it.name)
            }
        }

        return moviesGenres
    }

    fun getSpecificMovie(movieName: String) {
        searchPage = 1
        movieNameSearched = movieName
        APIService.service.getSpecificMovie(movieNameSearched, page = searchPage)
            .enqueue(object : Callback<MovieBodyResponse> {

                override fun onResponse(
                    call: Call<MovieBodyResponse>,
                    response: Response<MovieBodyResponse>
                ) {

                    if (response.isSuccessful) {
                        val movies: MutableList<Movie> = mutableListOf()

                        response.body()?.let { movieBodyResponse ->
                            for (result in movieBodyResponse.results) {
                                val movie = Movie(
                                    poster_path = result.poster_path,
                                    overview = result.overview,
                                    release_date = result.release_date,
                                    genres = getMovieGenres(result.genre_ids),
                                    original_language = result.original_language,
                                    title = result.title
                                )
                                movies.add(movie)
                            }
                        }

                        _searchScreenState.value = _searchScreenState.value?.copy(
                            foundMovies = movies,
                            isDefaultStateVisible = false,
                            isEmptyStateVisible = movies.isEmpty(),
                            isNonEmptyStateVisible = movies.isNotEmpty()
                        )
                    }
                }

                override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

                }
            })

    }

    fun incrementMoviesPage() {
        homePage++
        APIService.service.getMovies(page = homePage).enqueue(object : Callback<MovieBodyResponse> {
            override fun onResponse(
                call: Call<MovieBodyResponse>,
                response: Response<MovieBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val movies: MutableList<Movie> = mutableListOf()

                    response.body()?.let { movieBodyResponse ->
                        for (result in movieBodyResponse.results) {
                            val movie = Movie(
                                poster_path = result.poster_path,
                                overview = result.overview,
                                release_date = result.release_date,
                                genres = getMovieGenres(result.genre_ids),
                                original_language = result.original_language,
                                title = result.title
                            )
                            movies.add(movie)
                        }
                    }

                    val newMovies = _mainScreenState.value?.movies?.toMutableList() ?: mutableListOf()
                    newMovies.addAll(movies)

                    _mainScreenState.value = mainScreenState.value?.copy(
                        movies = newMovies
                    )
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

            }

        })
    }

    fun incrementSearchedMoviesPage() {
        searchPage++
        APIService.service.getSpecificMovie(movieNameSearched, page = searchPage)
            .enqueue(object : Callback<MovieBodyResponse> {

                override fun onResponse(
                    call: Call<MovieBodyResponse>,
                    response: Response<MovieBodyResponse>
                ) {

                    if (response.isSuccessful) {
                        val movies: MutableList<Movie> = mutableListOf()

                        response.body()?.let { movieBodyResponse ->
                            for (result in movieBodyResponse.results) {
                                val movie = Movie(
                                    poster_path = result.poster_path,
                                    overview = result.overview,
                                    release_date = result.release_date,
                                    genres = getMovieGenres(result.genre_ids),
                                    original_language = result.original_language,
                                    title = result.title
                                )
                                movies.add(movie)
                            }
                        }

                        val newMovies = _searchScreenState.value?.foundMovies?.toMutableList() ?: mutableListOf()
                        newMovies.addAll(movies)

                        _searchScreenState.value = _searchScreenState.value?.copy(
                            foundMovies = newMovies,
                            isDefaultStateVisible = false,
                            isEmptyStateVisible = newMovies.isEmpty(),
                            isNonEmptyStateVisible = newMovies.isNotEmpty()
                        )
                    }
                }

                override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

                }
            })

    }

}