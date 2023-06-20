package com.example.moviepopcorn.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviepopcorn.data.local.FavoriteMovie

//Interface FavoriteMovieDao berisi method yang berfungsi untuk
//mengakses database movie
@Dao
interface FavoriteMovieDao {

    //  fungsi untuk insert film favorite ke room db
    @Insert
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie)

    //  fungsi untuk menampilkan film yang telah difavoritkan berdasarkan liveData dari
    //  objek favorite movie
    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovie(): LiveData<List<FavoriteMovie>>

    //  fungsi untuk mengecek film yang telah difavoritkan di DB berdasarkan id
    @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id_movie = :id")
    suspend fun checkMovie(id: String): Int

    //  fungsi untuk menghapus film di DB berdasarkan id
    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id" )
    suspend fun removeFromFavorite(id: String) : Int
}