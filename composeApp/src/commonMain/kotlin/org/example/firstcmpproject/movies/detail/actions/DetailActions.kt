package org.example.firstcmpproject.movies.detail.actions

sealed class DetailActions {

    class OnTapBack(): DetailActions()
    class OnTapMovie(val movieId: Long): DetailActions()
}