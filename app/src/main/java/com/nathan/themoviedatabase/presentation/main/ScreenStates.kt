package com.nathan.themoviedatabase.presentation.main

import com.nathan.themoviedatabase.data.model.Movie

data class MainScreenState(
    val movies: List<Movie> = listOf()
)

data class SearchScreenState(
    val foundMovies: List<Movie> = listOf(),
    val isDefaultStateVisible : Boolean = true,
    val isEmptyStateVisible : Boolean = false,
    val isNonEmptyStateVisible : Boolean = false
)