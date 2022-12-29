package com.example.moviepopcorn.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteMovie::class],
    version = 1
)

//class FavoriteMovieDatabase akan mewarisi terhadap class RoomDatabase
abstract class FavoriteMovieDatabase : RoomDatabase(){
//    fungsi abstrak untuk mendapatkan data akses objek
    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
}