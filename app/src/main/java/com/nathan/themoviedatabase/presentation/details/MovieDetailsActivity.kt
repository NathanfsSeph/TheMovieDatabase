package com.nathan.themoviedatabase.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.nathan.themoviedatabase.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private val args: MovieDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = args.movie

        Glide.with(imageMovieDetailsPosterPath)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(imageMovieDetailsPosterPath)

        textMovieDetailsPosterPath.text = " "
        textMovieDetailsOverview.text = movie.overview
        textMovieDetailsReleaseDate.text = movie.release_date
        textMovieDetailsGenre.text = movie.genres.toString()
        textMovieDetailsOriginalLanguage.text = movie.original_language
        textMovieDetailsTitle.text = movie.title

    }

}