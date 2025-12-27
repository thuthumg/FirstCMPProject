package org.example.firstcmpproject.mock.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.persistence.daos.MovieDao

class MockMovieDao: MovieDao {

    val moviesFromDb : MutableList<MovieVO> = mutableListOf()

    override suspend fun insertMovies(movie: List<MovieVO>) {
        moviesFromDb.addAll(movie)
    }

    override suspend fun insertSingleMovie(movieVO: MovieVO) {
        moviesFromDb.add(movieVO)
    }

    override suspend fun getMovieById(movieId: Long): MovieVO? {
       return moviesFromDb.first{it.id == movieId}
    }

    override fun getMovieByIdFlow(movieId: Long): Flow<MovieVO?> {
      return  flowOf(moviesFromDb.firstOrNull {it.id == movieId})
    }

    override suspend fun getAllMovies(): List<MovieVO> {
       return moviesFromDb
    }

    override suspend fun deleteAllMovies() {
        moviesFromDb.clear()
    }
}