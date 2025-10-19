package org.example.firstcmpproject.movies.detail.state

import org.example.firstcmpproject.movies.data.vos.MovieVO

data class MovieDetailsState(
    val movieDetails: MovieVO? = null,
    val similarMovies: List<MovieVO> = listOf(),
    val loading: Boolean = false,
    val message: String = ""
)