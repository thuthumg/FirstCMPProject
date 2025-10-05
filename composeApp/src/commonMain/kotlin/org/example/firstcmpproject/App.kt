package org.example.firstcmpproject

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinx.serialization.Serializable
import org.example.firstcmpproject.auth.ui.NetflixLoginScreen
import org.example.firstcmpproject.core.NetflixSansTypography
import org.example.firstcmpproject.movies.detail.ui.MovieDetailsScreen
import org.example.firstcmpproject.movies.home.ui.HomeScreen

@Composable
@Preview
fun App() {

    val navController = rememberNavController()


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
                HomeScreen(
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

