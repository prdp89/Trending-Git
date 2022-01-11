package com.example.repodetailsui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.style.contributorsGradient
import com.example.trending.repo.RepoContributors


@ExperimentalCoilApi
@Composable
fun ContributorItem(contributors: RepoContributors) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(contributors.avatar),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .size(64.dp)
                .clip(CircleShape)
                .border(shape = CircleShape,
                    border = BorderStroke(
                        width = 3.dp,
                        brush = Brush.linearGradient(
                            colors = contributorsGradient,
                            start = Offset(
                                0f,
                                0f
                            ),
                            end = Offset(
                                100f,
                                100f
                            )
                        )
                    )
                )
        )
        contributors.name?.let { Text(text = it, style = typography.caption, textAlign = TextAlign.Center, modifier = Modifier.padding(8.dp)) }
    }
}