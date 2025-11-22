package org.example.firstcmpproject.movies.home.ui


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.movies.home.actions.HomeActions
import org.example.firstcmpproject.movies.home.events.HomeEvents
import org.example.firstcmpproject.movies.home.state.HomeState
import org.example.firstcmpproject.movies.home.viewmodel.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onTapMovie: (Long) -> Unit
) {

    //observe
    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit){
        viewModel.navigationSharedFlow.collectLatest { event ->
            when(event){
                is HomeEvents.NavigateToDetails -> {
                    onTapMovie(event.movieId)
                }
            }

        }
    }

    HomeScreen(
        state = state,
        onAction = { actions ->
            viewModel.onAction(actions)

        }
    )
}
@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeActions) -> Unit,
    //onTapMovie: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            HomeAppBar()
        },

        containerColor = Color.Black
    ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {

            item {
                //Movie Category
                MovieCategorySection(modifier = Modifier
                    .padding(start = MARGIN_CARD_MEDIUM_2))
            }

            item {
                //Feature Movie
                state.featureMovie?.let {
                    FeatureMovie(
                        movieVO= state.featureMovie,
                        onTapMovie = { movieId ->
                          //  onTapMovie(movieId)
                            onAction(HomeActions.OnTapMovie(movieId))
                        })
                }

            }

            //Spacer
            item { Spacer(
                modifier = Modifier.height(MARGIN_MEDIUM)
            ) }

            //Moves and Categories
            items(state.moviesByGenre.count()){ index ->
                CategoriesLabelAndMovies(
                    genre = state.moviesByGenre[index].first,
                    movieList = state.moviesByGenre[index].second,
                    onTapMovie = { movieId ->
                       // onTapMovie(movieId)
                        onAction(HomeActions.OnTapMovie(movieId))
                    })
            }
        }


    }
}

//@Preview
//@Composable
//fun HomeScreenPreview(modifier: Modifier = Modifier) {
//    HomeScreen(
//        state = HomeState(),
//        onTapMovie = {})
//}