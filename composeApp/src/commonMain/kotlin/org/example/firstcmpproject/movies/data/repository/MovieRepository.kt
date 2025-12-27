package org.example.firstcmpproject.movies.data.repository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.firstcmpproject.core.persistence.AppDatabase
import org.example.firstcmpproject.core.persistence.AppDatabaseProvider
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.network.api_service.ApiService
import org.example.firstcmpproject.movies.network.impls.ApiServiceImpl
import org.example.firstcmpproject.movies.persistence.daos.MovieDao

class MovieRepository (
    private val apiService: ApiService,
   // private val appDatabase: AppDatabase
    private val movieDao: MovieDao
){
//    val apiService: ApiService = ApiServiceImpl
//
//    val appDatabase = AppDatabaseProvider.appDatabase

    suspend fun getNowPlayingMovies(): List<MovieVO>{

        return withContext(Dispatchers.IO) {
            val response = apiService.getNowPlayingMovies(1)

            launch {
                movieDao.insertMovies(response?.results ?: listOf())
                println("Movies from db ===> ${movieDao.getAllMovies()}")
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

    suspend fun getMoviesWithFirstFiveGenresFlow(): List<Pair<GenreVO, List<MovieVO>>>{
       return getGenres()
            .asFlow()
            .take(5)
            .flatMapMerge { genreVO ->
                flow{
                    val moviesByGenre = getMoviesByGenres(genreId = genreVO.id)
                    emit(Pair(genreVO,moviesByGenre))
                }

            }.flowOn(Dispatchers.IO)
            .toList()
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
                movieDao.insertSingleMovie(response)
            }

            return@withContext response
        }
    }

    suspend fun getMovieDetailsFromDb(movieId : Long) : MovieVO?{
        return movieDao.getMovieById(movieId)

    }

     fun getMovieDetailsFromDbFlow(movieId : Long) : Flow<MovieVO?> {
        return movieDao.getMovieByIdFlow(movieId)
     }
}