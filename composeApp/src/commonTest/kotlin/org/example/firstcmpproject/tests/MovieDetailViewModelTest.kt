package org.example.firstcmpproject.tests

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.example.firstcmpproject.mock.data.MockMovieRepository
import org.example.firstcmpproject.mock.data.mockMovieDetails
import org.example.firstcmpproject.mock.data.mockMoviesByGenre
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.detail.actions.DetailActions
import org.example.firstcmpproject.movies.detail.events.DetailEvents
import org.example.firstcmpproject.movies.detail.state.MovieDetailsState
import org.example.firstcmpproject.movies.detail.viewmodel.MovieDetailsViewModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieDetailViewModelTest {

    private lateinit var vm: MovieDetailsViewModel
    private lateinit var repo: MovieRepository


    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repo = MockMovieRepository()
        vm = MovieDetailsViewModel(movieRepository = repo, movieId = 83533)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMovieDetailFromRepo_succeeds() = runTest{
        advanceUntilIdle()

        val currentState = vm.state.value

        val expectedData = mockMoviesByGenre[mockMovieDetails.genres?.first()?.id] ?: listOf()

        val expectedState = MovieDetailsState(
            movieDetails = mockMovieDetails,
            similarMovies = expectedData,
            loading = false,
            message = ""
        )

        assertEquals(expectedState,currentState)
    }

    @Test
    fun onTapMovie_navigateToMovieDetail() = runTest {
        vm.navigationSharedFlow.test{
            vm.onAction(DetailActions.OnTapMovie(28))
            val event = awaitItem()

            assertTrue(event is DetailEvents.NavigateToDetails)
            assertEquals(28, event.movieId)
        }
    }

    @Test
    fun onTapBack_navigateToHome() = runTest {
        vm.navigationSharedFlow.test {
            vm.onAction(DetailActions.OnTapBack())
            val event = awaitItem()

            assertTrue(event is DetailEvents.NavigateToHome)
        }
    }


}