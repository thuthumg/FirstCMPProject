package org.example.firstcmpproject

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinx.serialization.Serializable
import org.example.firstcmpproject.auth.ui.NetflixLoginScreen
import org.example.firstcmpproject.core.NetflixSansTypography
import org.example.firstcmpproject.movies.data.MovieRepository
import org.example.firstcmpproject.movies.detail.ui.MovieDetailsScreen
import org.example.firstcmpproject.movies.home.ui.HomeRoute
import org.example.firstcmpproject.movies.home.ui.HomeScreen
import org.example.firstcmpproject.movies.home.viewmodel.HomeViewModel
import org.example.firstcmpproject.movies.network.impls.ApiServiceImpl

@Composable
@Preview
fun App() {

    val navController = rememberNavController()
//
//
//    LaunchedEffect(Unit){
//
//        val moviesGenres = MovieRepository.getMoviesWithFirstFiveGenres()
//        println("First five movies by genre ==> $moviesGenres")
//
//
//
//    }
//

    MaterialTheme(
        typography = NetflixSansTypography()
    ) {
      // NetflixLoginScreen()
      //  HomeScreen()
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Login//NavRoutes.MovieDetails(0)//NavRoutes.Login
        ){
            composable<NavRoutes.Login> {
                NetflixLoginScreen(
                    onTapSignIn = {
                        navController.navigate(NavRoutes.Home)
                    }
                )
            }

            composable<NavRoutes.Home> {

                val homeViewModel = viewModel { HomeViewModel() }
                HomeRoute(
                    viewModel = homeViewModel,
                    onTapMovie = { movieId ->

                        navController.navigate(NavRoutes.MovieDetails(
                            movieId = movieId))
                    }
                )

            }

            composable<NavRoutes.MovieDetails> { backStackEntry ->
                val args = backStackEntry.toRoute<NavRoutes.MovieDetails>()
                val movieId = args.movieId

                MovieDetailsScreen(
                    onTapBack = {
                        print("onTapBack case")
                        navController.navigateUp()
                    },

                    onTapMovie = {
                    navController.navigate(
                        NavRoutes.MovieDetails(movieId)
                    )
                },movieId)
            }
        }
    }
}

@Serializable
sealed class NavRoutes{

    @Serializable
    object Login


    @Serializable
    object Home

    @Serializable
    data class MovieDetails(val movieId: Int)

}

