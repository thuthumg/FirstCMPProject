package org.example.firstcmpproject.movies.data.repository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.firstcmpproject.core.persistence.AppDatabaseProvider
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.network.api_service.ApiService
import org.example.firstcmpproject.movies.network.impls.ApiServiceImpl

object MovieRepository {
    val apiService: ApiService = ApiServiceImpl

    val appDatabase = AppDatabaseProvider.appDatabase

    suspend fun getNowPlayingMovies(): List<MovieVO>{

        return withContext(Dispatchers.IO) {
            val response = apiService.getNowPlayingMovies(1)

            launch {
                appDatabase.movieDao().insertMovies(response?.results ?: listOf())
                println("Movies from db ===> ${appDatabase.movieDao().getAllMovies()}")
            }

            return@withContext response?.results ?: listOf()
        }

    }

    suspend fun getFeaturedMovie(): MovieVO?{

        //Get Now Playing Movies -> First -> Use id of first movie ->  Get Movie Details -> return
        return withContext(Dispatchers.IO) {

            return@withContext getMovieDetail(getNowPlayingMovies().first().id)
        }

    }

    suspend fun getMoviesWithFirstFiveGenres(): List<Pair<GenreVO, List<MovieVO>>>{
        return withContext(Dispatchers.IO) {
            val genres = getGenres()

            val moviesByGenreDeferredList: List<Deferred<Pair<GenreVO, List<MovieVO>>>> =
                genres.take(5).map { genre ->
                    async {
                        val moviesByGenre = getMoviesByGenres(genre.id)
                        return@async Pair(genre, moviesByGenre)
                    }

                }

            moviesByGenreDeferredList.awaitAll()

        }
    }

    suspend fun getGenres() : List<GenreVO>{
        return withContext(Dispatchers.IO) {
            val response = apiService.getGenres()
            return@withContext response?.genres ?: listOf()
        }
    }

    suspend fun getMoviesByGenres(genreId: Int) : List<MovieVO>{
        return withContext(Dispatchers.IO) {
            val response = apiService.getMoviesByGenres(genreId)
            return@withContext response?.results ?: listOf()
        }
    }


    suspend fun getMovieDetail(movieId: Long) : MovieVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.getMovieDetail(movieId)


            launch {
                appDatabase.movieDao().insertSingleMovie(response)
            }

            return@withContext response
        }
    }

    suspend fun getMovieDetailsFromDb(movieId : Long) : MovieVO?{
        return appDatabase.movieDao().getMovieById(movieId)

    }
}