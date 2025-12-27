package org.example.firstcmpproject.mock.data

import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.network.api_service.ApiService
import org.example.firstcmpproject.movies.network.responses.GenreListResponse
import org.example.firstcmpproject.movies.network.responses.MovieListResponse

class MockMovieApiService : ApiService{
    override suspend fun getNowPlayingMovies(page: Int): MovieListResponse? {
        return MovieListResponse(
            dates = null,
            page = 1,
             mockNowPlayingMovies
        )
    }

    override suspend fun getGenres(): GenreListResponse? {
        return GenreListResponse(
            genres = mockGenres
        )
    }

    override suspend fun getMoviesByGenres(genreId: Int): MovieListResponse? {
       return MovieListResponse(
           dates = null,
           page = 1,
           results = mockMoviesByGenre[genreId] ?: listOf()
       )
    }

    override suspend fun getMovieDetail(movieId: Long): MovieVO {
        return mockMovieDetails
    }

}