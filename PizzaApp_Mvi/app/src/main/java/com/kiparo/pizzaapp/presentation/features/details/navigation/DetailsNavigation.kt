package com.kiparo.pizzaapp.presentation.features.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kiparo.pizzaapp.app.di.DiProvider
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetSingleMenuItemUseCase
import com.kiparo.pizzaapp.presentation.features.details.DetailsScreen
import com.kiparo.pizzaapp.presentation.features.details.DetailsViewModel


fun NavHostController.navigateToDetails(menuItemId: String) {
    navigate("${DetailsDestination.route}/${menuItemId}")
}


data object DetailsDestination : KiparoPizzaDestination {
    const val argumentName = "menuItem"
    override val route = "details"
    val routeWithArgs = "${route}/{${argumentName}}"
    val arguments =
        listOf(navArgument(argumentName) { type = NavType.StringType })
}

fun NavGraphBuilder.details(onNavigateUp: () -> Unit) {
    composable(
        route = DetailsDestination.routeWithArgs,
        arguments = DetailsDestination.arguments
    ) {
        val viewModel: DetailsViewModel = viewModel(
            factory = DetailsViewModel.Factory(
                getSingleMenuItemUseCase = DiProvider.di.get(GetSingleMenuItemUseCase::class),
                addToCartUseCase = DiProvider.di.get(AddToCartUseCase::class)
            )
        )
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        DetailsScreen(
            uiState = uiState,
            addToCart = viewModel::addToCart,
            onNavigateUp = onNavigateUp
        )
    }
}