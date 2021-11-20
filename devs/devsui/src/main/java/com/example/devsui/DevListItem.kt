package com.example.devsui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.style.Shapes
import com.example.trending.dev.TrendingDev

@Composable
fun DevListItem(devItem : TrendingDev,
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemImage(
                Modifier.padding(end = 16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text("title", style = typography.subtitle1)
                    Text("title_1", style = typography.subtitle1, modifier = Modifier.padding(start = 8.dp))
                }
                Text("subtitle", style = typography.body2, fontWeight = FontWeight.Bold)
                Text("Description here...", style = typography.body2, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun ItemImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_sleep),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
    )
}