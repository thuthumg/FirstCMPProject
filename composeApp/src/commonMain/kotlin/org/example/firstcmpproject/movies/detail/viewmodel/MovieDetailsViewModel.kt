package org.example.firstcmpproject.movies.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firstcmpproject.redux.Actions
import org.example.firstcmpproject.redux.AppState
import org.reduxkotlin.Store
import org.reduxkotlin.StoreSubscription


class MovieDetailsViewModel(val movieId: Long,val store: Store<AppState>) : ViewModel(){

    //State
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    var subscription : StoreSubscription? = null

    init {

        store.dispatch(Actions.MiddlewareActions.FetchMovieDetailsAndSimilarMovies(movieId))
        store.dispatch(Actions.MiddlewareActions.GetMovieDetailsFromDb(movieId))

        viewModelScope.launch {
            subscription = store.subscribe{
                _state.update {
                    store.state
                }
            }
        }
    }

    override fun onCleared() {
        subscription?.invoke()
        super.onCleared()
    }
}