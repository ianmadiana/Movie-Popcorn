package com.example.moviepopcorn.api

import com.example.moviepopcorn.data.model.Movie

data class MovieResponse(
    val results: List<Movie>
)
