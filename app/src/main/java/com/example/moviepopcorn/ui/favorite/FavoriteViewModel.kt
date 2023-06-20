package com.example.moviepopcorn.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepopcorn.data.local.FavoriteMovie
import com.example.moviepopcorn.data.local.FavoriteMovieRepository

//class untuk favorite view model yang diinject
//class ini mewarisi dari view model
class FavoriteViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel(){
//    variabel movies akan menampung data dari favorite movies
    val movies = repository.getFavoriteMovies()
}