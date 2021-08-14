package com.sample.reposui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RepoListUi() {
    Column {
        Text(text ="This is List screen", color = Color.Blue)
    }
}