package org.example.firstcmpproject.movies.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import firstcmpproject.composeapp.generated.resources.Res
import firstcmpproject.composeapp.generated.resources.image_not_supported
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM_3
import org.example.firstcmpproject.core.MARGIN_SMALL
import org.example.firstcmpproject.core.TEXT_REGULAR_2X
import org.example.firstcmpproject.core.utils.FEATURE_MOVIE_IMAGE
import org.example.firstcmpproject.movies.MovieActionButton
import org.example.firstcmpproject.movies.data.vos.MovieVO
import org.jetbrains.compose.resources.painterResource

@Composable
fun FeatureMovie(movieVO: MovieVO, onTapMovie: (Long) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MARGIN_MEDIUM_3,
                start = MARGIN_MEDIUM_2,
                end = MARGIN_MEDIUM_2
            )
            .height(500.dp)
    ) {

        SubcomposeAsyncImage(
            model = movieVO.getFullMoviePosterPath(),
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(MARGIN_CARD_MEDIUM_2))
                .clickable {
                    onTapMovie(movieVO.id)
                }.testTag(FEATURE_MOVIE_IMAGE),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                 CircularProgressIndicator(modifier = Modifier.size(30.dp))
            },
            error = {
                Icon(
                    painter = painterResource(Res.drawable.image_not_supported),
                    contentDescription = "Error loading image"
                )
            },

            )

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Black
                    )
                )
            ).fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM_2)
            ) {

                //Genres
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val movieGenreList = movieVO.genres?.take(3)
                    movieGenreList?.forEachIndexed{ index, genre ->

                        Text(
                            genre.name, color = Color.White,
                            fontSize = TEXT_REGULAR_2X,
                            fontWeight = FontWeight.Medium
                        )
                        if(index != (movieGenreList.size-1))
                        {
                            Surface(
                                color = Color.White,
                                shape = CircleShape,
                                modifier = Modifier.size(MARGIN_SMALL)
                            ) {}
                        }

                    }

                }

                FeaturedMovieButtons()

            }
        }
    }
}

@Composable
fun FeaturedMovieButtons() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_2),
        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM_2)

    )
    {
        MovieActionButton(
            backgroundColor = Color.White,
            iconVector = Icons.Default.PlayArrow,
            iconPainter = null,
            label = "Play",
            contentColor = Color.Black,
            modifier = Modifier.weight(1.0f)
        )
        MovieActionButton(
            backgroundColor = Color.DarkGray,
            iconVector = Icons.Default.Add,
            iconPainter = null,
            label = "My List",
            contentColor = Color.White,
            modifier = Modifier.weight(1.0f)
        )
    }
}
