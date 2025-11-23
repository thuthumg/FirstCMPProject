package org.example.firstcmpproject.redux

import kotlinx.coroutines.launch
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

val asyncMiddleware = middleware { store: Store<AppState>, next: Dispatcher, action: Any ->
    val movieRepository = MovieRepository
    val scope = AppCoroutineScope
    when (action) {
        Actions.MiddlewareActions.FetchFeaturedMovies -> {
            scope.coroutineScope.launch {
                val featuredMovie = movieRepository.getNowPlayingMovies().firstOrNull()
                next(Actions.ReducerActions.FeaturedMovieSuccessful(featuredMovie))
            }
        }

        Actions.MiddlewareActions.FetchMoviesByGenre -> {
            scope.coroutineScope.launch {
                val moviesByGenre = movieRepository.getMoviesWithFirstFiveGenres()
                next(Actions.ReducerActions.MoviesByGenreSuccessful(moviesByGenre))
            }
        }

        is Actions.MiddlewareActions.FetchMovieDetailsAndSimilarMovies -> {
            scope.coroutineScope.launch {
                val movieDetails = movieRepository.getMovieDetail(action.movieId)
                next(Actions.ReducerActions.FetchMovieDetailsSuccessful(movieDetails))

                movieDetails.genres?.firstOrNull()?.let {
                    val similarMovies = movieRepository.getMoviesByGenres(it.id)
                    next(Actions.ReducerActions.FetchSimilarMoviesSuccessful(similarMovies))
                }


            }
        }


        is Actions.MiddlewareActions.GetMovieDetailsFromDb -> {
            scope.coroutineScope.launch {
                val moviesDetail = movieRepository.getMovieDetailsFromDb(action.movieId)
                next(Actions.ReducerActions.GetMovieDetailsDbSuccessful(moviesDetail))
            }
        }

    }

}