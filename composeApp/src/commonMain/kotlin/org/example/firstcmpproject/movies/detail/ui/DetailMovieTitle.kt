package org.example.firstcmpproject.movies.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.TEXT_LARGE

@Composable
fun DetailMovieTitle(modifier: Modifier = Modifier) {
    Text("Gladiator 2",
        color= Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = TEXT_LARGE,
        modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2)

    )
}