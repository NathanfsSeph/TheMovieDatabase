package com.nathan.themoviedatabase.presentation.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathan.themoviedatabase.data.APIService
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.data.response.MovieBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _mainScreenState : MutableLiveData<MainScreenState> = MutableLiveData()
    val mainScreenState : LiveData<MainScreenState> get() = _mainScreenState

    private val _searchScreenState : MutableLiveData<SearchScreenState> = MutableLiveData()
    val searchScreenState : LiveData<SearchScreenState> get() = _searchScreenState

    init {
        _mainScreenState.value = MainScreenState()
        _searchScreenState.value = SearchScreenState()
    }

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

                    _mainScreenState.value = mainScreenState.value?.copy(
                        movies = movies
                    )
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {

            }

        })
    }

    fun getSpecificMovie(movieName : String){
//        val uri: Uri = Uri.parse(movieName)
        val uri = "The Batman"
        APIService.service.getSpecificMovie(uri).enqueue(object: Callback<MovieBodyResponse>{

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

                    Log.i("Alow",movies.toString())

                    _searchScreenState.value = _searchScreenState.value?.copy(
                        foundMovies = movies
                    )
                }
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
                Log.i("tag",uri)
            }
        })

        // Pegar o livro, guardar no livedata, observar o livedata na bottom sheet, se tiver encontrado o livro fechar a bottom sheet e ir pra tela de detalhes,
        // caso não encontrar, exibir uma mensagem pro usuário

    }

}