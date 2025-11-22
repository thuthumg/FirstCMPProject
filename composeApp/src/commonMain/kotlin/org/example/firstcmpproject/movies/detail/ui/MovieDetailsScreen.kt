package org.example.firstcmpproject.movies.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.firstcmpproject.core.MARGIN_34
import org.example.firstcmpproject.core.MARGIN_40
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_LARGE
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_SMALL
import org.example.firstcmpproject.core.MARGIN_XLARGE
import org.example.firstcmpproject.core.MOVIE_ITEM_HEIGHT
import org.example.firstcmpproject.core.TEXT_REGULAR
import org.example.firstcmpproject.core.TEXT_REGULAR_3X
import org.example.firstcmpproject.core.TEXT_SMALL
import org.example.firstcmpproject.movies.MovieItem
import org.example.firstcmpproject.movies.detail.state.MovieDetailsState
import org.example.firstcmpproject.movies.detail.viewmodel.MovieDetailsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collectLatest
import org.example.firstcmpproject.movies.detail.actions.DetailActions
import org.example.firstcmpproject.movies.detail.events.DetailEvents

@Composable
fun MovieDetailsRoute(viewModel: MovieDetailsViewModel,
                      onTapMovie: (Long) -> Unit,
                      onTapBack: () -> Unit) {


    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit){
        viewModel.navigationSharedFlow.collectLatest { event ->
            when(event){
                is DetailEvents.NavigateToDetails ->{
                    onTapMovie(event.movieId)
                }
                is DetailEvents.NavigateToHome -> onTapBack
            }

        }

    }


    MovieDetailsScreen(
        state = state,
        onActions = { actions ->
            viewModel.onAction(actions)

        }
    )

}



@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    onActions: (DetailActions) -> Unit
   // onTapBack: () -> Unit,
   // onTapMovie: (Long) -> Unit
    ) {
    Scaffold (
        containerColor = Color.Black
    ){

        state.movieDetails?.let {
            LazyColumn {

                //Movie Image
                item {
                    DetailMovieImage(
                        movieVO = it,
                        onTapBack = {
                            onActions(DetailActions.OnTapBack())
                        })
                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM))
                }

                //LOGO
                item {
                    DetailMovieLogo()
                }
                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM))
                }
                //Movie Title
                item {
                    DetailMovieTitle(
                        it.title
                    )
                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM))
                }

                //Movie Info
                item {
                    DetailMovieInfo(
                        movieVO = it
                    )
                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))
                }

                //Play and Download buttons
                item{
                    MovieDetailsButtons()
                }



                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))
                }

                //Spacer
                item {
                    Text(
                        it.overview,
                        color = Color.White,
                        fontSize = TEXT_REGULAR,
                        modifier = Modifier.padding(
                            horizontal = MARGIN_CARD_MEDIUM_2
                        )
                    )
                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))
                }

                //Cast
                item {
                    Text(
                        "Cast: Taron Egerton,Sofia Carson, Jason Bateman...more",
                        color= Color.DarkGray,
                        fontSize = TEXT_SMALL,
                        modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2)
                    )
                }

                //Director
                item {
                    Text(
                        "Director: Jaume Collet-Serra",
                        color= Color.DarkGray,
                        fontSize = TEXT_SMALL,
                        modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2)
                    )
                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))
                }

                //Detail Action Buttons
                item {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            MARGIN_40
                        ),
                        modifier = Modifier.padding(horizontal = MARGIN_XLARGE)
                    ) {
                        MovieDetailsActionButton(
                            icon = Icons.Default.Add,
                            title = "My List",
                            modifier = Modifier
                        )
                        MovieDetailsActionButton(
                            icon = Icons.Default.ThumbUp,
                            title = "Rate",
                            modifier = Modifier
                        )

                        MovieDetailsActionButton(
                            icon = Icons.Default.Share,
                            title = "Share",
                            modifier = Modifier
                        )
                    }



                }

                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_LARGE))
                }

                item {
                    Text(
                        "More Like This",
                        color = Color.White,
                        fontSize = TEXT_REGULAR_3X,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            horizontal = MARGIN_MEDIUM_2
                        )
                    )
                }
                //Spacer
                item {
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM))
                }
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(horizontal = MARGIN_MEDIUM_2),
                        verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                        modifier = Modifier.height(
                            (MOVIE_ITEM_HEIGHT+MARGIN_CARD_MEDIUM_2) * ((state.similarMovies.count() /3 ) +1)
                        )
                    ){
                        items(state.similarMovies){ it ->
                            MovieItem (
                                movieVO = it,
                                onTapMovie = {
                                    onActions(DetailActions.OnTapMovie(it))
                                }
                            )
                        }
                    }
                }
            }
        }



    }
}

@Composable
fun MovieDetailsActionButton(icon: ImageVector,title: String,modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            MARGIN_SMALL
        ),
        modifier = modifier
    ) {
        Icon(icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(MARGIN_34))

        Text(
            title,
            color = Color.White
        )

    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    // MovieDetailsScreen(onTapBack = {}, onTapMovie = {})
}