package com.example.moviepopcorn.api

import com.example.moviepopcorn.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

//Class ini digunakan untuk membuat API dari TMDB
interface MovieApi {

//    companion object di bawah digunakan agar fungsi bisa dipanggil tanpa melalui objek
    companion object{
//    base URL dari TMDB
        const val BASE_URL = "https://api.themoviedb.org/3/"
//    API key dari TMDB
        const val API_KEY = BuildConfig.TMDB_API_KEY
    }

//    fungsi untuk memanggil API yang dijalankan di background
//    anotasi yang dipanggil adalah anotasi dari retrofit
    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlaying(
        @Query("page") position: Int
    ): MovieResponse

//  fungsi untuk mencari film dengan end point API
    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}