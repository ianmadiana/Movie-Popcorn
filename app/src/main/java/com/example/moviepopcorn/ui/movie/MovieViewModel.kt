package com.example.moviepopcorn.ui.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviepopcorn.data.MovieRepository

//class view model untuk movie
class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted state: SavedStateHandle) : ViewModel(){

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap { query ->
        if (!query.isEmpty()){
            repository.getSearchMovies(query)
        }else{
            repository.getNowPlaying().cachedIn(viewModelScope)
        }
    }

//    fungsi untuk mencari film berdasarkan query di state
    fun searchMovies(query: String){
        currentQuery.value = query
    }
}