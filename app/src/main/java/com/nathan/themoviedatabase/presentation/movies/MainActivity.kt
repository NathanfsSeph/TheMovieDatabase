package com.nathan.themoviedatabase.presentation.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.commons.readAssetsFile
import com.nathan.themoviedatabase.data.model.Genres
import com.nathan.themoviedatabase.presentation.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        toolbar.title = getString(R.string.movies_title)
        setSupportActionBar(toolbar)

        val gson = Gson()
        val genres: Genres = gson.fromJson(this.assets.readAssetsFile("genres.json"), Genres::class.java)
        Log.i("Genres", genres.toString() )


        val viewModel : MoviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        viewModel.moviesLiveData.observe(this, Observer{
            it?.let { movies ->
                with(recyclerView) {
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(movies) {
                        val intent = MovieDetailsActivity.getStartIntent(this@MainActivity, it)
                        this@MainActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.getMovies()


    }



}