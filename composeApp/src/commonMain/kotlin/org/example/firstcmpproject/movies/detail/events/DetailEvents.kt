package org.example.firstcmpproject.movies.detail.events


sealed class DetailEvents {

    class NavigateToHome(): DetailEvents()
    class NavigateToDetails(val movieId: Long): DetailEvents()
}