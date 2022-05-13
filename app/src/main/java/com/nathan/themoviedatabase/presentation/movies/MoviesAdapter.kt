package com.nathan.themoviedatabase.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.data.response.MovieResultsResponse
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(
    private val movies : List<Movie>,
    val onItemClickListener : ((movie : Movie) -> Unit)
    ): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent,false)

        return MoviesViewHolder(itemView,onItemClickListener)
    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        viewHolder.bindView(movies[position] )
    }

    class MoviesViewHolder(itemView : View, private val onItemClickListener : ((movie : Movie) -> Unit)) : RecyclerView.ViewHolder(itemView){

        private val poster_path = itemView.textViewPoster
        private val release_date = itemView.textViewReleaseDate
        private val genre_ids = itemView.textViewGenre
        private val title = itemView.textViewTitle

        fun bindView(movie : Movie){

            poster_path.text = movie.poster_path
            release_date.text = movie.release_date
            genre_ids.text = movie.genre_ids.toString()
            title.text = movie.title

            itemView.setOnClickListener{
                onItemClickListener.invoke(movie)
            }

        }
    }

}