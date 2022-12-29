package com.example.moviepopcorn.api

import com.example.moviepopcorn.data.model.Movie

//data class ini digunakan untuk men-generate data JSON
//yang didalamnya ada results list berupa objek-objek dari film
data class MovieResponse(
    val results: List<Movie>
)
