package com.example.reposui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.style.Shapes
import com.example.trending.TrendingRepo

@Composable
fun RepoListItem( repoItem : TrendingRepo,
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
                .clickable(onClick = { })
                .padding(16.dp)
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text("title", style = typography.subtitle1)
                Text("subtitle", style = typography.body2)
            }
        }
    }
}

@Composable
fun ItemImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = 0),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(100.dp, 80.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}