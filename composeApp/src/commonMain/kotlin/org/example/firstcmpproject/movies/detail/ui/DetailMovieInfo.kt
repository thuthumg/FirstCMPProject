package org.example.firstcmpproject.movies.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import firstcmpproject.composeapp.generated.resources.Res
import firstcmpproject.composeapp.generated.resources.ad_pic
import firstcmpproject.composeapp.generated.resources.dolby_vision
import firstcmpproject.composeapp.generated.resources.message
import firstcmpproject.composeapp.generated.resources.spatial_audio
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.TEXT_REGULAR
import org.example.firstcmpproject.core.TEXT_REGULAR_2X
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailMovieInfo(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2)

    ){
        Text("2024",
            color = Color.White,
            fontSize = TEXT_REGULAR_2X
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(20.dp)
                .width(25.dp).background(Color.Gray,
                    shape = RoundedCornerShape(5.dp),
                )){
            Text("16+",
                color = Color.White,
                fontSize = TEXT_REGULAR
            )
        }

        Text("1h 59m",
            color = Color.White,
            fontSize = TEXT_REGULAR_2X
        )

        Icon(
            painterResource(Res.drawable.dolby_vision),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(20.dp)
                .width(30.dp).background(Color.Transparent,
                    shape = RoundedCornerShape(5.dp),
                ).border(
                    width = 1.dp,
                    color = Color.Gray
                ),

            ){
            Text("HD",
                color = Color.White,
                fontSize = TEXT_REGULAR
            )
        }

        Icon(
            painterResource(Res.drawable.spatial_audio),
            contentDescription = null,
            tint = Color.White.copy(0.7f),
            modifier = Modifier.size(25.dp)
        )

        Icon(
            painterResource(Res.drawable.ad_pic),
            contentDescription = null,
            tint = Color.White.copy(0.7f),
            modifier = Modifier.size(25.dp)
        )

        Icon(
            painterResource(Res.drawable.message),
            contentDescription = null,
            tint = Color.White.copy(0.7f),
            modifier = Modifier.size(25.dp)
        )
    }
}