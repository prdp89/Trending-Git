package com.example.favoriteui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.core.entity.FavoriteRepoEntity
import com.example.style.Shapes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteListItem(repoItem : FavoriteRepoEntity,
                     onLong: (() -> Unit)) {
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
                .padding(16.dp)
                .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onLong()
                    })
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            repoItem.avatar?.let {
                ItemImage(
                    it,
                    Modifier.padding(end = 16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                repoItem.name?.let { Text(it, style = typography.body2, fontWeight = FontWeight.Bold) }
                repoItem.description?.let { Text(it, style = typography.body2, fontWeight = FontWeight.Medium) }
            }
        }
    }
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