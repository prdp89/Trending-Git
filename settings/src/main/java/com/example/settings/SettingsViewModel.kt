package com.example.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val defaultPreferences: DefaultPreferences,
) : ViewModel() {

    val darkTheme = defaultPreferences.darkTheme.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    fun changeTheme(isDarkThemeEnabled: Boolean) {
        viewModelScope.launch { defaultPreferences.changeTheme(isDarkThemeEnabled) }
    }
}