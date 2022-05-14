package com.nathan.themoviedatabase.presentation.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.presentation.details.MovieDetailsActivity
import com.nathan.themoviedatabase.presentation.main.MainViewModel
import com.nathan.themoviedatabase.presentation.main.adapter.MoviesAdapter
import com.nathan.themoviedatabase.presentation.main.adapter.OnMovieListener
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment(), OnMovieListener {

    private val viewModel: MainViewModel by sharedViewModel()
    private val adapter: MoviesAdapter by lazy { MoviesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.searchScreenState.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateDataSet(it.foundMovies)
            }
        }
    }

    private fun setupViews() {
        with(foundMoviesRecyclerView) {
            adapter = this@SearchFragment.adapter
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = MovieDetailsActivity.getStartIntent(requireContext(), movie)
        startActivity(intent)
    }

}