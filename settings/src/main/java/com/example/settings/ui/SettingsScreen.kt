package com.example.settings.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.settings.R
import com.example.settings.SettingsViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.settings_screen)) },
                elevation = 8.dp,
                actions = {

                })
        }, content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars
                )
            ) {
                item { DarkTheme(viewModel.darkTheme) { viewModel.changeTheme(it) } }
                item { VersionNumber() }
            }
        }
    )
}

@Composable
fun DarkTheme(
    darkThemeFlow: StateFlow<Boolean>,
    changeTheme: (theme: Boolean) -> Unit
) {
    val darkTheme = darkThemeFlow.collectAsState().value
    SettingsItem(modifier = Modifier
        .clickable {
            changeTheme(!darkTheme)
        }
        .padding(top = 8.dp)) {
        CheckBoxWithText(text = com.example.settings.R.string.dark_theme,
            isChecked = darkTheme,
            checkChanged = {
                changeTheme(it)
            })
    }
}

@Composable
fun SettingsItem(modifier: Modifier = Modifier, item: @Composable (BoxScope) -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        item(this)
    }
}

@Composable
fun CheckBoxWithText(
    @StringRes text: Int,
    isChecked: Boolean,
    checkChanged: (Boolean) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        val (textWidget, checkboxWidget) = createRefs()
        Text(
            text = stringResource(id = text),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textWidget) {
                    start.linkTo(parent.start)
                    end.linkTo(checkboxWidget.start)
                    width = Dimension.fillToConstraints
                    centerVerticallyTo(parent)
                }
                .padding(start = 8.dp, end = 4.dp),
            textAlign = TextAlign.Start
        )

        Switch(
            checked = isChecked, onCheckedChange = checkChanged,
            modifier = Modifier
                .constrainAs(checkboxWidget) {
                    centerVerticallyTo(parent)
                    end.linkTo(parent.end)
                }
                .padding(start = 8.dp, end = 4.dp)
        )
    }
}

fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, 0).versionName

@Composable
fun VersionNumber() {
    TitleWithSubtitleTextItem(titleText = stringResource(id = com.example.settings.R.string.version),
        subtitleText = LocalContext.current.getVersionName())
}

@Composable
private fun TitleWithSubtitleTextItem(titleText: String, subtitleText: String) {
    SettingsItem(modifier = Modifier
        .padding(vertical = 8.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            Text(text = titleText, modifier =
            Modifier.padding(horizontal = 8.dp))

            Text(text = subtitleText,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 4.dp),
                fontSize = 12.sp, color = Color.Gray
            )

        }
    }
}