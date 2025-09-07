package org.example.firstcmpproject.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import firstcmpproject.composeapp.generated.resources.Res
import firstcmpproject.composeapp.generated.resources.downwards_arrow_key
import firstcmpproject.composeapp.generated.resources.lalpalma_poster
import org.example.firstcmpproject.core.MARGIN_40
import org.example.firstcmpproject.core.MARGIN_CARD_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_LARGE
import org.example.firstcmpproject.core.MARGIN_MEDIUM
import org.example.firstcmpproject.core.MARGIN_MEDIUM_2
import org.example.firstcmpproject.core.MARGIN_MEDIUM_3
import org.example.firstcmpproject.core.MARGIN_SMALL
import org.example.firstcmpproject.core.MARGIN_XXLARGE
import org.example.firstcmpproject.core.TEXT_REGULAR_2X
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
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
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MARGIN_MEDIUM_3,
                        start = MARGIN_MEDIUM_2,
                        end = MARGIN_MEDIUM_2)
                    .height(500.dp)){
                    Image(
                        painterResource(Res.drawable.lalpalma_poster),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(MARGIN_CARD_MEDIUM_2)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }


    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen()
}