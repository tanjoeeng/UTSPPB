package com.andikajayaw.movietrailerapp.model.movie

import com.andikajayaw.movietrailerapp.model.movie.MovieModel

data class MovieResponse (
    val total_pages: Int,
    val results:List<MovieModel>,
)