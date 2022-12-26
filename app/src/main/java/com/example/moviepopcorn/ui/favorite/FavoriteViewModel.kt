package com.example.moviepopcorn.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepopcorn.data.local.FavoriteMovie
import com.example.moviepopcorn.data.local.FavoriteMovieRepository

class FavoriteViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel(){
    val movies = repository.getFavoriteMovies()
}