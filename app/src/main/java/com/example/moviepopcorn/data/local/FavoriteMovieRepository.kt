package com.example.moviepopcorn.data.local

import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
){
//    memanggil semua fungsi yang ada di interface FavoriteMovieDao
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie) = favoriteMovieDao.addToFavorite(favoriteMovie)
    fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovie()
    suspend fun checkMovie(id: String) = favoriteMovieDao.checkMovie(id)
    suspend fun removeFromFavorite(id: String) {
        favoriteMovieDao.removeFromFavorite(id)
    }
}