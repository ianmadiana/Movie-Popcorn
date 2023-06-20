package com.example.moviepopcorn.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//class ini digunakan sebagai model data yang akan digunakan
//model data didapatkan dari API TMDB berupa JSON
@Parcelize
data class Movie(
    val id: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val vote_average: String,
    val poster_path: String
): Parcelable {
//    script ini digunakan sebagai base URL untuk gambar/poster film
    val baseUrl get() ="https://image.tmdb.org/t/p/w500"
}