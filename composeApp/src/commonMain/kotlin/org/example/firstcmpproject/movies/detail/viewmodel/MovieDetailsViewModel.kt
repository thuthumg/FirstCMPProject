package org.example.firstcmpproject.movies.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firstcmpproject.movies.data.repository.MovieRepository
import org.example.firstcmpproject.movies.detail.actions.DetailActions
import org.example.firstcmpproject.movies.detail.events.DetailEvents
import org.example.firstcmpproject.movies.detail.state.MovieDetailsState


class MovieDetailsViewModel(val movieId: Long,
                            private val movieRepository: MovieRepository) : ViewModel(){

    //Repository


    //State
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

//
//    private val _navigateToBackSharedFlow:MutableSharedFlow<Boolean>   = MutableSharedFlow()
//
//    val navigateToBackSharedFlow = _navigateToBackSharedFlow.asSharedFlow()
//
//    private val _navigateToDetailsSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow()
//    val navigateToDetailsSharedFlow = _navigateToDetailsSharedFlow.asSharedFlow()


    private val _navigationSharedFlow : MutableSharedFlow<DetailEvents> = MutableSharedFlow()

    val navigationSharedFlow = _navigationSharedFlow.asSharedFlow()




    init {

        // Network
        viewModelScope.launch {
            val movieDetails = movieRepository.getMovieDetail(movieId)
            _state.update{ it.copy(movieDetails =  movieDetails)}


            movieDetails.genres?.firstOrNull()?.id?.let { genreId ->
                val similarMovies = movieRepository.getMoviesByGenres(genreId).toMutableList()

                similarMovies.removeAll {
                    it.id == movieId
                }


                _state.update { it.copy(similarMovies = similarMovies) }
            }
        }

        //Persistence
        viewModelScope.launch {
//            val movieDetails = movieRepository.getMovieDetailsFromDb(movieId)
//            _state.update{ it.copy(movieDetails =  movieDetails)}

            movieRepository.getMovieDetailsFromDbFlow(movieId).collect{
                movieDetails ->  _state.update{ it.copy(movieDetails =  movieDetails)}
            }
        }
    }

//    fun onTapMovie(movieId: Long){
//        viewModelScope.launch {
//            _navigateToDetailsSharedFlow.emit(movieId)
//        }
//    }
//
//    fun onTapBack(){
//        viewModelScope.launch {
//            _navigateToBackSharedFlow.emit(true)
//        }
//    }

    fun onAction(action: DetailActions){
        when(action){
            is DetailActions.OnTapBack -> {
                viewModelScope.launch {

                    _navigationSharedFlow.emit(DetailEvents.NavigateToHome())
                }
            }
            is DetailActions.OnTapMovie -> {
                viewModelScope.launch {
                    _navigationSharedFlow.emit(DetailEvents.NavigateToDetails(movieId = action.movieId))
                }
            }
        }
    }
}