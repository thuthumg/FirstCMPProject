package org.example.firstcmpproject.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import firstcmpproject.composeapp.generated.resources.Res
import firstcmpproject.composeapp.generated.resources.image_not_supported
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MOVIE_ITEM_HEIGHT
import org.example.firstcmpproject.core.MOVIE_ITEM_WIDTH
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.example.firstcmpproject.movies.home.ui.ShimmerBox
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieItem(
    movieVO: MovieVO?,
    onTapMovie: (Long) -> Unit) {

    movieVO?.let {
        SubcomposeAsyncImage(
            model= movieVO.getFullMovieBackdropPath() ,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(MOVIE_ITEM_WIDTH)
                .height(MOVIE_ITEM_HEIGHT)
                .clip(RoundedCornerShape(MARGIN_MEDIUM))
                .clickable{
                    onTapMovie(movieVO.id)
                },
            loading = {
                // Composable to display while the image is loading
                // CircularProgressIndicator(modifier = Modifier.size(30.dp))
                ShimmerBox(Modifier.fillMaxSize())
            },
            error = {
                // Composable to display if image loading fails
                Icon(
                    painter = painterResource(Res.drawable.image_not_supported),
                    contentDescription = "Error loading image"
                )
            },
            success = {
                // Composable to display when the image is successfully loaded
                SubcomposeAsyncImageContent()
            }
        )

    }

}