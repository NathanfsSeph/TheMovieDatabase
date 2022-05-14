package com.nathan.themoviedatabase.presentation.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.data.model.Movie
import com.nathan.themoviedatabase.presentation.details.MovieDetailsActivity
import com.nathan.themoviedatabase.presentation.main.MoviesViewModel
import com.nathan.themoviedatabase.presentation.main.adapter.MoviesAdapter
import com.nathan.themoviedatabase.presentation.main.adapter.OnMovieListener
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(), OnMovieListener {

    private val viewModel: MoviesViewModel by sharedViewModel()
    private val adapter: MoviesAdapter by lazy { MoviesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) {
            it?.let { movies ->
                adapter.updateDataSet(movies)
            }
        }
    }

    private fun setupViews() {
        with(recyclerView) {
            adapter = this@MainFragment.adapter
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = MovieDetailsActivity.getStartIntent(requireContext(), movie)
        startActivity(intent)
    }

}
