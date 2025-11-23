package org.example.firstcmpproject.redux

import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO

data class AppState (

    //Home Screen
    val featureMovie: MovieVO? = null,
    val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>> = listOf(),

    //MovieDetail
    val movieDetails: MovieVO? = null,
    val similarMovies: List<MovieVO> = listOf(),


    val isLoading: Boolean = false,
    val err: String = ""
)