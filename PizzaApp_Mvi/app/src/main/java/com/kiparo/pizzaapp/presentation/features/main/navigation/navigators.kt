package com.kiparo.pizzaapp.presentation.features.main.navigation

import androidx.navigation.NavHostController
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.presentation.features.auth.navigation.AUTH_ROUTE
import com.kiparo.pizzaapp.presentation.features.auth.navigation.AuthNavigator
import com.kiparo.pizzaapp.presentation.features.auth.navigation.navigateToAuth
import com.kiparo.pizzaapp.presentation.features.auth.navigation.navigateToRegister
import com.kiparo.pizzaapp.presentation.features.auth.navigation.navigateToReset
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.BOTTOM_MENU_ROUTE
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.BottomMenuNavigator
import com.kiparo.pizzaapp.presentation.features.details.navigation.navigateToDetails
import com.kiparo.pizzaapp.presentation.features.start.navigation.StartNavigator

fun NavHostController.startNavigator(): StartNavigator =
    object : StartNavigator {
        override fun onNavigateAfterStarted() {
            popBackStack()
            navigateToAuth()
        }
    }

fun NavHostController.authNavigator()
        : AuthNavigator = object : AuthNavigator {
    override fun onNavigateAfterLogin() {
        popBackStack()
        navigateSingleTopTo(BOTTOM_MENU_ROUTE)
    }

    override fun onNavigateToRegister() {
        navigateToRegister()
    }

    override fun onNavigateToReset() {
        navigateToReset()
    }

    override fun onNavigateUp() {
        popBackStack()
    }
}

fun NavHostController.bottomNavigator(): BottomMenuNavigator =
    object : BottomMenuNavigator {
        override fun onNavigateToDetails(menuItemId: String) {
            navigateToDetails(menuItemId = menuItemId)
        }

        override fun onLogout() {
            popBackStack()
            navigateToAuth()
        }

        override fun onNavigateUp() {
            popBackStack()
        }
    }