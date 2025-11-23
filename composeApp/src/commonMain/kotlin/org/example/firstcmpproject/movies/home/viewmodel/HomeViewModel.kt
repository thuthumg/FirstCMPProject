package org.example.firstcmpproject.movies.home.viewmodel

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

class HomeViewModel(val store: Store<AppState>): ViewModel() {


    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    var subscription : StoreSubscription? = null

    init {
        //Featured Movie
        store.dispatch(Actions.MiddlewareActions.FetchFeaturedMovies)
        store.dispatch(Actions.MiddlewareActions.FetchMoviesByGenre)

        viewModelScope.launch {
            subscription = store.subscribe{
              _state.update { store.state }
            }
        }

    }

    override fun onCleared() {
        subscription?.invoke()
        super.onCleared()
    }
}