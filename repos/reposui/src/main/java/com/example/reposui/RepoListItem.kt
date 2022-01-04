package com.example.reposui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.util.CoilUtils
import com.example.style.Shapes
import com.example.trending.repo.TrendingRepo

@Composable
fun RepoListItem(repoItem : TrendingRepo,
                 onClick: () -> Unit) {

    Card(
        shape = Shapes.large,
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val typography = MaterialTheme.typography
        Row(
            modifier = Modifier
                .clickable(onClick = { onClick() })
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repoItem.avatar?.let {
                ItemImage(
                    it,
                    Modifier.padding(end = 16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                repoItem.author?.let { Text(it, style = typography.subtitle1) }
                repoItem.name?.let { Text(it, style = typography.body2, fontWeight = FontWeight.Bold) }
                repoItem.description?.let { Text(it, style = typography.body2, fontWeight = FontWeight.Medium) }

                ItemCount(repoItem, Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun ItemCount(repoItem: TrendingRepo, modifier: Modifier) {
    Row(modifier = modifier.fillMaxHeight()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(alignment = Alignment.CenterVertically)
            )
            repoItem.language?.let { Text(it, Modifier.padding(start = 8.dp)) }
        }
        Row(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(R.drawable.star_yellow_16),
                contentDescription = "Content description for visually impaired"
            )
            DrawCountText(repoItem.stars, Modifier.padding(start = 8.dp))
        }
        Row(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(R.drawable.fork_black_16),
                contentDescription = "Content description for visually impaired"
            )
            DrawCountText(repoItem.forks, Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun DrawCountText(str: Int?, modifier: Modifier) {
    Text(str.toString(), modifier)
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemImage(imagePath: String, modifier: Modifier = Modifier) {

    Image(
        painter = rememberImagePainter(imagePath),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
    )
}