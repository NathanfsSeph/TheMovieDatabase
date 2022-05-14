package com.nathan.themoviedatabase.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.nathan.themoviedatabase.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.movies_title)
        setSupportActionBar(toolbar)

        viewModel.getMovies()

        setupWithNavController(
            bottomNavigationView,
            findNavController(R.id.navHostFragment)
        )

    }



}