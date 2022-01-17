package com.example.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.style.Shapes

@Preview
@Composable
fun PreviewBackButton() {
    BackButton()
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}, ) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier)
    {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}