package com.example.moviepopcorn.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val vote_average: String,
    val poster_path: String
): Parcelable {
    val baseUrl get() ="https://image.tmdb.org/t/p/w500"
}