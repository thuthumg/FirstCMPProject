package org.example.firstcmpproject.movies.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO

interface MovieRepository {

    suspend fun getNowPlayingMovies(): List<MovieVO>
    suspend fun getFeaturedMovie(): MovieVO?
    suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>>

    //suspend fun getMoviesWithFirstFiveGenresFlow(): List<Pair<GenreVO, List<MovieVO>>>

    suspend fun getGenres() : List<GenreVO>
    suspend fun getMoviesByGenres(genreId: Int) : List<MovieVO>

    suspend fun getMovieDetail(movieId: Long) : MovieVO

    suspend fun getMovieDetailsFromDb(movieId : Long) : MovieVO?

    fun getMovieDetailsFromDbFlow(movieId : Long) : Flow<MovieVO?>
}