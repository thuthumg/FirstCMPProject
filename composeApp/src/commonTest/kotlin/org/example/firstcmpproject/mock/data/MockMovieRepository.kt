package org.example.firstcmpproject.mock.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import kotlin.math.exp

class MockMovieRepository : MovieRepository{
    override suspend fun getNowPlayingMovies(): List<MovieVO> {
        return mockNowPlayingMovies
    }

    override suspend fun getFeaturedMovie(): MovieVO? {
       return mockNowPlayingMovies.firstOrNull()
    }

    override suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>> {
        val expectedData: MutableList<Pair<GenreVO,List<MovieVO>>> = mutableListOf()
        mockGenres.take(5).forEach {
            val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
            expectedData.add(Pair(it, moviesByGenre))
        }

        return expectedData
    }
//
//    override suspend fun getMoviesWithFirstFiveGenresFlow(): List<Pair<GenreVO, List<MovieVO>>> {
//        TODO("Not yet implemented")
//    }

    override suspend fun getGenres(): List<GenreVO> {
        return mockGenres
    }

    override suspend fun getMoviesByGenres(genreId: Int): List<MovieVO> {
        return mockMoviesByGenre[genreId] ?: listOf()
    }

    override suspend fun getMovieDetail(movieId: Long): MovieVO {
        return mockMovieDetails
    }

    override suspend fun getMovieDetailsFromDb(movieId: Long): MovieVO? {
       return mockMovieDetails
    }

    override fun getMovieDetailsFromDbFlow(movieId: Long): Flow<MovieVO?> {
        return flowOf(mockMovieDetails)
    }

}