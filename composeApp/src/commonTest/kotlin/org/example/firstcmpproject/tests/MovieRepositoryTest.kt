package org.example.firstcmpproject.tests

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.example.firstcmpproject.mock.data.MockMovieApiService
import org.example.firstcmpproject.mock.data.MockMovieDao
import org.example.firstcmpproject.mock.data.mockGenres
import org.example.firstcmpproject.mock.data.mockMovieDetails
import org.example.firstcmpproject.mock.data.mockMoviesByGenre
import org.example.firstcmpproject.mock.data.mockNowPlayingMovies
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.network.api_service.ApiService
import org.example.firstcmpproject.movies.persistence.daos.MovieDao
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MovieRepositoryTest {

    private lateinit var repo: MovieRepository
    private lateinit var dao: MovieDao
    private lateinit var apiService: ApiService

    @BeforeTest
    fun setUp(){
         dao = MockMovieDao()
        apiService = MockMovieApiService()
        repo = MovieRepository(apiService = apiService, movieDao = dao)
    }



    @Test
    fun getNowPlayingMovies_apiSucceeds() = runTest{
        val nowPlayingMovies = repo.getNowPlayingMovies()
        assertEquals(mockNowPlayingMovies,nowPlayingMovies)
        assertNotNull(repo.getMovieDetailsFromDb(
            83533
        ))
    }

    @Test
    fun getFeaturedMovie_apiSucceeds() = runTest {
        val featureMovie = repo.getFeaturedMovie()
        assertNotNull(featureMovie)
        assertEquals(mockMovieDetails,featureMovie)
    }

    @Test
    fun getMovieDetailsFromDbFlow_apiSucceeds() = runTest {
        val movieId = mockNowPlayingMovies.first().id

        val movieFromDb = repo.getMovieDetailsFromDbFlow(movieId).firstOrNull()
        assertNull(movieFromDb)

        repo.getNowPlayingMovies()
        val movieFromDbSecond = repo.getMovieDetailsFromDbFlow(movieId).firstOrNull()
        assertNotNull(movieFromDbSecond)
        assertEquals(mockNowPlayingMovies.first(), movieFromDbSecond)
    }

    @Test
    fun getMoviesWithFirstFiveGenres_apiSucceeds() = runTest{
        val actual = repo.getMoviesWithFirstFiveGenres()

        val expectedData: MutableList<Pair<GenreVO,List<MovieVO>>> = mutableListOf()
        mockGenres.take(5).forEach {
            val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
            expectedData.add(Pair(it, moviesByGenre))
        }

        assertEquals(expectedData,actual)
    }

    @Test
    fun getGenres_apiSucceeds() = runTest{
        val genres = repo.getGenres()
        assertEquals(mockGenres,genres)

    }

    @Test
    fun getMoviesByGenres_apiSucceeds() = runTest{

        val genresId = mockGenres.first().id

        val movieByGenre = repo.getMoviesByGenres(genresId)
        assertTrue(movieByGenre.isNotEmpty())

        assertEquals(mockMoviesByGenre[genresId] ?: listOf<MovieVO>(),movieByGenre)
    }

    @Test
    fun getMovieDetail_apiSucceeds() = runTest {
        val movieId = mockNowPlayingMovies.first().id

        val movieDetail = repo.getMovieDetail(movieId)
        assertNotNull(movieDetail)

        assertEquals(mockMovieDetails, movieDetail)

    }





}