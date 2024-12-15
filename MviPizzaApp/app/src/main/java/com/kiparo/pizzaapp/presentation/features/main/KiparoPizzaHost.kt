package com.kiparo.pizzaapp.presentation.features.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.presentation.features.auth.navigation.AUTH_ROUTE
import com.kiparo.pizzaapp.presentation.features.auth.navigation.authentication
import com.kiparo.pizzaapp.presentation.features.auth.navigation.navigateToRegister
import com.kiparo.pizzaapp.presentation.features.auth.navigation.navigateToReset
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.BOTTOM_MENU_ROUTE
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.bottom_menu
import com.kiparo.pizzaapp.presentation.features.main.navigation.authNavigator
import com.kiparo.pizzaapp.presentation.features.main.navigation.bottomNavigator
import com.kiparo.pizzaapp.presentation.features.main.navigation.startNavigator
import com.kiparo.pizzaapp.presentation.features.start.navigation.StartDestination
import com.kiparo.pizzaapp.presentation.features.start.navigation.start

@Composable
fun KiparoPizzaHost(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route
    ) {
        start(navController.startNavigator())
        authentication(navController.authNavigator())
        bottom_menu(navController.bottomNavigator())
    }
}
