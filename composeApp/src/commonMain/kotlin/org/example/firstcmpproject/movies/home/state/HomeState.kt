package org.example.firstcmpproject.movies.home.state

import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO

data class HomeState (
    val featureMovie: MovieVO? = null,
    val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>> = listOf(),
    val loading: Boolean = false,
    val message: String = ""
)