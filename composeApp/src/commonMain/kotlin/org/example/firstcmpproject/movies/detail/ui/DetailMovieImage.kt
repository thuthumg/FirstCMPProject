package org.example.firstcmpproject.movies.detail.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MARGIN_XLARGE
import org.example.firstcmpproject.core.MARGIN_XXLARGE
import org.example.firstcmpproject.core.utils.MOVIE_DETAIL_IMAGE
import org.example.firstcmpproject.movies.data.vos.MovieVO

@Composable
fun DetailMovieImage(
    movieVO: MovieVO,
    onTapBack: () -> Unit,modifier: Modifier = Modifier) {

    Box(modifier = Modifier.fillMaxWidth().height(250.dp)){
        AsyncImage(
            model = movieVO.getFullMoviePosterPath(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().testTag(MOVIE_DETAIL_IMAGE),
            contentScale = ContentScale.Crop,
            // error = painterResource(Res.drawable.gladiator_photo)
        )
        Icon(
            Icons.AutoMirrored.Default.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(MARGIN_XLARGE)
                .align(Alignment.TopStart)
                .offset(x = MARGIN_MEDIUM, y = MARGIN_XXLARGE)
                .clickable{
                    onTapBack()
                }
        )
    }
}