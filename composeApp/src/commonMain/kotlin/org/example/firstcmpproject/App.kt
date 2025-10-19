package org.example.firstcmpproject

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.RoomDatabase
import kotlinx.serialization.Serializable
import org.example.firstcmpproject.auth.ui.NetflixLoginScreen
import org.example.firstcmpproject.core.NetflixSansTypography
import org.example.firstcmpproject.core.persistence.AppDatabase
import org.example.firstcmpproject.core.persistence.AppDatabaseProvider
import org.example.firstcmpproject.movies.detail.ui.MovieDetailsRoute
import org.example.firstcmpproject.movies.detail.viewmodel.MovieDetailsViewModel
import org.example.firstcmpproject.movies.home.ui.HomeRoute
import org.example.firstcmpproject.movies.home.viewmodel.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(databaseBuilder : RoomDatabase.Builder<AppDatabase>) {


    AppDatabaseProvider.initializeDatabase(databaseBuilder)


    val navController = rememberNavController()

    MaterialTheme(
        typography = NetflixSansTypography()
    ) {

        NavHost(
            navController = navController,
            startDestination = NavRoutes.Login
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

                val movieDetailsViewModel = viewModel { MovieDetailsViewModel(movieId) }

                MovieDetailsRoute(
                    viewModel = movieDetailsViewModel,
                    onTapMovie = {
                        navController.navigate(
                            NavRoutes.MovieDetails(it)
                        )
                    },
                    onTapBack = {
                        navController.navigateUp()
                    }
                )

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
    data class MovieDetails(val movieId: Long)

}

