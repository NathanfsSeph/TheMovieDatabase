package com.nathan.themoviedatabase.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.data.model.Movie
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movie_item.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_activity)

        textMovieDetailsPosterPath.text = intent.getStringExtra(EXTRA_POSTER_PATH)
        textMovieDetailsOverview.text = intent.getStringExtra(EXTRA_OVERVIEW)
        textMovieDetailsReleaseDate.text = intent.getStringExtra(EXTRA_RELEASE_DATE)
        textMovieDetailsGenre.text = intent.getStringExtra(EXTRA_GENRE_IDS)
        textMovieDetailsOriginalLanguage.text = intent.getStringExtra(EXTRA_ORIGINAL_LANGUAGE)
        textMovieDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)

    }

    companion object {

        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_OVERVIEW = "EXTRA_OVERVIEW"
        private const val EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE"
        private const val EXTRA_GENRE_IDS = "EXTRA_GENRE_IDS"
        private const val EXTRA_ORIGINAL_LANGUAGE = "EXTRA_ORIGINAL_LANGUAGE"
        private const val EXTRA_TITLE = "EXTRA_TITLE"

        fun getStartIntent(context : Context, movie : Movie ) : Intent {

            return Intent(context,MovieDetailsActivity::class.java).apply{
                putExtra(EXTRA_POSTER_PATH, movie.poster_path)
                putExtra(EXTRA_OVERVIEW, movie.overview)
                putExtra(EXTRA_RELEASE_DATE, movie.release_date)
                putExtra(EXTRA_GENRE_IDS, movie.genre_ids.toString())
                putExtra(EXTRA_ORIGINAL_LANGUAGE, movie.original_language)
                putExtra(EXTRA_TITLE, movie.title)
            }

        }
    }
}