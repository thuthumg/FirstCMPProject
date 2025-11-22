package org.example.firstcmpproject.movies.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.home.actions.HomeActions
import org.example.firstcmpproject.movies.home.events.HomeEvents
import org.example.firstcmpproject.movies.home.state.HomeState

class HomeViewModel: ViewModel() {

    val movieRepository = MovieRepository

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

//
//    private val _navigateToDetailsSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow()
//    val navigateToDetailsSharedFlow = _navigateToDetailsSharedFlow.asSharedFlow()



    private val _navigationSharedFlow : MutableSharedFlow<HomeEvents> = MutableSharedFlow()

    val navigationSharedFlow = _navigationSharedFlow.asSharedFlow()



    init {
        //Featured Movie
        viewModelScope.launch {
          val featuredMovie =  movieRepository.getFeaturedMovie()
            _state.update {
                it.copy(featuredMovie)
            }
        }

//        viewModelScope.launch {
//            val moviesByGenre = movieRepository.getMoviesWithFirstFiveGenres()
//            _state.update {
//                it.copy(moviesByGenre = moviesByGenre)
//            }
//        }

        viewModelScope.launch {
            val moviesByGenre = movieRepository.getMoviesWithFirstFiveGenresFlow()
            _state.update {
                it.copy(moviesByGenre = moviesByGenre)
            }
        }

    }
// reactive flow
//    fun onTapMovie(movieId: Long){
//        viewModelScope.launch {
//            _navigateToDetailsSharedFlow.emit(movieId)
//        }
//    }

    fun onAction(action: HomeActions){
        when(action){
            is HomeActions.OnTapMovie -> {
                viewModelScope.launch {
                    _navigationSharedFlow.emit(HomeEvents.NavigateToDetails(movieId = action.movieId))
                }
            }
        }
    }
}