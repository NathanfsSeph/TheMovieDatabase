package com.nathan.themoviedatabase.data.main

import androidx.recyclerview.widget.DiffUtil
import com.nathan.themoviedatabase.data.response.MovieResultsResponse

class ItemDiffCallback : DiffUtil.ItemCallback<MovieResultsResponse>() {
    override fun areItemsTheSame(oldItem: MovieResultsResponse, newItem: MovieResultsResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieResultsResponse, newItem: MovieResultsResponse): Boolean {
        return oldItem == newItem
    }
}