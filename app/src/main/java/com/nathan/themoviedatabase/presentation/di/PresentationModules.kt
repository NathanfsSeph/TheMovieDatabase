package com.nathan.themoviedatabase.presentation.di

import com.nathan.themoviedatabase.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {

    viewModel {
        MainViewModel()
    }

}