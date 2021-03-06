package com.nathan.themoviedatabase.presentation.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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
                defaultState.isVisible = it.isDefaultStateVisible
                emptyState.isVisible = it.isEmptyStateVisible
                nonEmptyState.isVisible = it.isNonEmptyStateVisible
                adapter.updateDataSet(it.foundMovies)
            }
        }
    }

    private fun setupViews() {
        val searchEditText: EditText =
            searchSearchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))
        searchSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(query.isNotEmpty()){
                    viewModel.getSpecificMovie(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        with(foundMoviesRecyclerView) {
            adapter = this@SearchFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(!recyclerView.canScrollHorizontally(1)){
                        viewModel.incrementSearchedMoviesPage()
                    }
                }
            })
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsActivity(movie)
        findNavController().navigate(action)
    }

}