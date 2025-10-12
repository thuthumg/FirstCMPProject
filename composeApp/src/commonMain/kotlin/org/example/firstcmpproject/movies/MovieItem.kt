package org.example.firstcmpproject.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MOVIE_ITEM_HEIGHT
import org.example.firstcmpproject.core.MOVIE_ITEM_WIDTH
import org.example.firstcmpproject.movies.data.vos.MovieVO

@Composable
fun MovieItem(
    movieVO: MovieVO?,
    onTapMovie: (Int) -> Unit) {
    AsyncImage(
        movieVO?.getFullMovieBackdropPath() ,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(MOVIE_ITEM_WIDTH)
            .height(MOVIE_ITEM_HEIGHT)
            .clip(RoundedCornerShape(MARGIN_MEDIUM))
            .clickable{
                onTapMovie(0)
            }
    )
}