package com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.presentation.features.bottom_menu.BottomMenuScreen
import com.kiparo.pizzaapp.presentation.features.details.navigation.details


const val BOTTOM_MENU_ROUTE = "bottom_menu"

private object BottomMenuDestination : KiparoPizzaDestination {
    override val route = BOTTOM_MENU_ROUTE
}


interface BottomMenuNavigator {
    fun onNavigateToDetails(menuItemId: String)
    fun onLogout()
    fun onNavigateUp()
}


fun NavGraphBuilder.bottom_menu(externalNavigator: BottomMenuNavigator) {
    composable(BottomMenuDestination.route) {
        BottomMenuScreen(externalNavigator)
    }
    details(onNavigateUp = externalNavigator::onNavigateUp)
}