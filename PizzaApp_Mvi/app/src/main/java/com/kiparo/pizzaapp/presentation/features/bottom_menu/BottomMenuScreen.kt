package com.kiparo.pizzaapp.presentation.features.bottom_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kiparo.pizzaapp.app.di.DiProvider
import com.kiparo.pizzaapp.core.design.SystemBarsColorDisposableEffect
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.navigation.TopDestinationsCollection
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.BottomMenuNavigator
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.menuNavigator
import com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation.profileNavigator
import com.kiparo.pizzaapp.presentation.features.bottom_menu.widgets.KiparoPizzaBottomBar
import com.kiparo.pizzaapp.presentation.features.cart.navigation.cart
import com.kiparo.pizzaapp.presentation.features.menu.navigation.MENU_GRAPH
import com.kiparo.pizzaapp.presentation.features.menu.navigation.menu
import com.kiparo.pizzaapp.presentation.features.profile.navigation.profile
import com.kiparo.pizzaapp.presentation.features.promo.navigation.promotion

@Composable
fun BottomMenuScreen(externalNavigator: BottomMenuNavigator) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.parent?.route
        ?: navBackStackEntry?.destination?.route

    val bottomMenuViewModel: BottomMenuViewModel = viewModel(
        factory = BottomMenuViewModel.Factory(
            getCartUseCase = DiProvider.di.get(ObserveCartUseCase::class),
            topLevelDestinationsCollection = DiProvider.di.get(TopDestinationsCollection::class)
        )
    )
    val bottomMenuUiState by bottomMenuViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(bottomBar = {
        KiparoPizzaBottomBar(
            bottomUiState = bottomMenuUiState,
            currentDestination = currentRoute,
            onNavigateToTopLevel = { route ->
                navController.navigateSingleTopTo(route)
            })
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = MENU_GRAPH
            ) {
                menu(navController.menuNavigator(externalNavigator))
                promotion()
                cart()
                profile(navController.profileNavigator(externalNavigator))
            }

            SystemBarsColorDisposableEffect(true)
        }
    }
}
@Preview
@Composable
fun BottomMenuScreenPreview() {
    KiparoPizzaAppTheme {
        BottomMenuScreen(
            externalNavigator = object: BottomMenuNavigator{
                override fun onNavigateToDetails(menuItemId: String) {
                    TODO("Not yet implemented")
                }

                override fun onLogout() {
                    TODO("Not yet implemented")
                }

                override fun onNavigateUp() {
                    TODO("Not yet implemented")
                }
            }

        )
    }
}