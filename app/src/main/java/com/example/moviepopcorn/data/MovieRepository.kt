package com.example.moviepopcorn.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviepopcorn.api.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi){

//  fungsi untuk memanggil konten Now Playing dari API TMDB
//  Page size yang akan diload dalam satu kali permintaan adalah 5 page
//  Data ini berbentuk LiveData
    fun getNowPlaying() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(movieApi,null)}
        ).liveData

    //  fungsi untuk memanggil proses pencarian film di TMDB berdasarkan kata kunci string
    fun getSearchMovies(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(movieApi,query)}
        ).liveData

}