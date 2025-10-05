package org.example.firstcmpproject.movies.home.ui


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(onTapMovie: (Int) -> Unit) {
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
               FeatureMovie(onTapMovie = { movieId ->
                   onTapMovie(movieId)
               })
            }

            //Spacer
            item { Spacer(
                modifier = Modifier.height(MARGIN_MEDIUM)
            ) }

            //Moves and Categories
            items(10){
               CategoriesLabelAndMovies(onTapMovie = { movieId ->
                   onTapMovie(movieId)
               })
            }
        }


    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(onTapMovie = {})
}