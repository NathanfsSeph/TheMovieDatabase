package com.nathan.themoviedatabase.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIService {

    private fun initRetrofit() : Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : TMDbServices = initRetrofit().create(TMDbServices::class.java)

}