package com.example.moviepopcorn.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.moviepopcorn.data.local.FavoriteMovie
import com.example.moviepopcorn.data.local.FavoriteMovieRepository
import com.example.moviepopcorn.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//View Model untuk details
//class ini akan mewarisi class view model
class DetailsMovieModel @ViewModelInject constructor(
    private val repository : FavoriteMovieRepository
) : ViewModel(){
    fun addToFavorite(movie: Movie){
//        coroutine untuk background
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
//                memasukkan object favorite movie
                FavoriteMovie(
                    movie.id,
                    movie.original_title,
                    movie.overview,
                    movie.popularity,
                    movie.vote_average,
                    movie.poster_path
                )
            )
        }
    }

//    fungsi untuk cek movie berdasarkan id
    suspend fun checkMovie(id: String) = repository.checkMovie(id)

//    fungsi untuk hapus movie berdasarkan id
    fun removeFromFavorite(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }
}