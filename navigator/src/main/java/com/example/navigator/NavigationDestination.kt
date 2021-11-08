
package com.example.navigator

import androidx.navigation.compose.NamedNavArgument

/**
 * Created by funkymuse on 6/25/21 to long live and prosper !
 */
fun interface NavigationDestination {

    fun route(): String
    val arguments: List<NamedNavArgument>
        get() = emptyList()
}