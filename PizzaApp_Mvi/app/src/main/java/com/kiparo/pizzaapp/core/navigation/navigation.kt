package com.kiparo.pizzaapp.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

interface KiparoPizzaDestination{
    val route: String
}

data class TopDestinationsCollection(val items: List<KiparoTopLevelDestination>)


interface KiparoTopLevelDestinationWithCount{
    val badgeValue: Int
    fun copyWithNewBadge(value: Int): KiparoTopLevelDestination
}

interface KiparoTopLevelDestination{
    val graph: String
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
}

// Describe the navigation stack according to the presentation
fun NavHostController.navigateSingleTopTo(route: String) {
    navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}