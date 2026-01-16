package org.example.firstcmpproject.movies.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.TEXT_LARGE
import org.example.firstcmpproject.core.utils.MOVIE_NAME

@Composable
fun DetailMovieTitle(
    movieTitle: String
) {
    Text(movieTitle,
        color= Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = TEXT_LARGE,
        modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2).testTag(MOVIE_NAME)

    )
}