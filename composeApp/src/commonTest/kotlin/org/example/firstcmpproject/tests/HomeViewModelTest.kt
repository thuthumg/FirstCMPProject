package org.example.firstcmpproject.tests

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.example.firstcmpproject.mock.data.MockMovieRepository
import org.example.firstcmpproject.mock.data.mockGenres
import org.example.firstcmpproject.mock.data.mockMoviesByGenre
import org.example.firstcmpproject.mock.data.mockNowPlayingMovies
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.data.vos.GenreVO
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.home.actions.HomeActions
import org.example.firstcmpproject.movies.home.events.HomeEvents
import org.example.firstcmpproject.movies.home.state.HomeState
import org.example.firstcmpproject.movies.home.viewmodel.HomeViewModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeViewModelTest {
    private lateinit var vm : HomeViewModel
    private lateinit var repo: MovieRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repo = MockMovieRepository()
        vm = HomeViewModel(
            movieRepository = repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDataFromRepo_succeeds() = runTest {
        advanceUntilIdle()
        val currentState = vm.state.value

        val expectedData: MutableList<Pair<GenreVO,List<MovieVO>>> = mutableListOf()
        mockGenres.take(5).forEach {
            val moviesByGenre = mockMoviesByGenre[it.id] ?: listOf()
            expectedData.add(Pair(it, moviesByGenre))
        }

        val expectedState = HomeState(
            featureMovie = mockNowPlayingMovies.firstOrNull(),
            moviesByGenre = expectedData,
            loading = false,
            message = ""
        )

        assertEquals(expectedState,currentState)

    }

    @Test
    fun onTapMovie_navigateToMovieDetails() = runTest {
        vm.navigationSharedFlow.test{
            vm.onAction(HomeActions.OnTapMovie(28))
            val event = awaitItem()

            assertTrue(event is HomeEvents.NavigateToDetails)
            assertEquals(28, event.movieId)
        }
    }

}