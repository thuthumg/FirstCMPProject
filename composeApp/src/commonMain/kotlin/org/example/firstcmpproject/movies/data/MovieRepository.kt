package org.example.firstcmpproject.movies.data

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.network.api_service.ApiService
import org.example.firstcmpproject.movies.network.impls.ApiServiceImpl
import org.example.firstcmpproject.movies.network.responses.MovieListResponse
import org.example.firstcmpproject.movies.persistence.MovieDao

object MovieRepository {
    val apiService: ApiService = ApiServiceImpl

    val movieDao = MovieDao

    suspend fun getNowPlayingMovies(): MovieListResponse? {

        return withContext(Dispatchers.IO){
            val response = apiService.getNowPlayingMovies(1)

            launch {
                movieDao.insertMovies(response?.results ?: listOf())
            }

            return@withContext response
        }

    }

    suspend fun getFeaturedMovie(): MovieVO?{

        //Get Now Playing Movies -> First -> Use id of first movie ->  Get Movie Details -> return
        return withContext(Dispatchers.IO){


            return@withContext getMovieDetail(getNowPlayingMovies()?.results?.first()?.id ?: -1)
        }


    }

    suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>>{
        return withContext(Dispatchers.IO){
            val genres = getGenres()

           val moviesByGenreDeferredList: List<Deferred<Pair<GenreVO,List<MovieVO>>>> = genres.take(5).map { genre ->
                async {
                    val moviesByGenre = getMoviesByGenres(genre.id)
                    return@async Pair(genre, moviesByGenre)
                }

            }

             moviesByGenreDeferredList.awaitAll()

        }
    }

    suspend fun getGenres() : List<GenreVO>{
        return withContext(Dispatchers.IO){
            val response = apiService.getGenres()
            return@withContext response?.genres ?: listOf()
        }
    }

    suspend fun getMoviesByGenres(genreId: Int) : List<MovieVO>{
        return withContext(Dispatchers.IO){
            val response = apiService.getMoviesByGenres(genreId)
            return@withContext response?.results ?: listOf()
        }
    }


    suspend fun getMovieDetail(movieId: Long) : MovieVO{
        return withContext(Dispatchers.IO){
            val response = apiService.getMovieDetail(movieId)
            return@withContext response
        }
    }

    suspend fun getMovieDetailsFromDb(movieId: Int): MovieVO?{
        return withContext(Dispatchers.IO){
            movieDao.getMovieById(movieId)
        }
    }
}