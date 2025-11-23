package org.example.firstcmpproject.redux

import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO

sealed interface Actions {

    sealed interface MiddlewareActions{

        //Home Screen
        object FetchFeaturedMovies: MiddlewareActions
        object FetchMoviesByGenre: MiddlewareActions

        //Details Screen
        data class FetchMovieDetailsAndSimilarMovies(val movieId: Long) : MiddlewareActions
        data class GetMovieDetailsFromDb(val movieId: Long): MiddlewareActions
    }

    sealed interface ReducerActions{

        //Home Screen
        data class FeaturedMovieSuccessful(val featuredMovie: MovieVO?): ReducerActions
        data class MoviesByGenreSuccessful(val moviesByGenre: List<Pair<GenreVO, List<MovieVO>>>): ReducerActions

        //Details Screen
        data class FetchMovieDetailsSuccessful(val movieDetails: MovieVO?): ReducerActions
        data class FetchSimilarMoviesSuccessful(val similarMovies: List<MovieVO>): ReducerActions

        data class GetMovieDetailsDbSuccessful(val movieDetails: MovieVO?): ReducerActions
    }
}