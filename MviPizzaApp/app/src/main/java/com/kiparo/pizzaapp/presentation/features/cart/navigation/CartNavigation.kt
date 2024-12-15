package com.kiparo.pizzaapp.presentation.features.cart.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.app.di.DiProvider
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestinationWithCount
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.RemoveFromCartUseCase
import com.kiparo.pizzaapp.presentation.features.cart.CartScreen
import com.kiparo.pizzaapp.presentation.features.cart.CartViewModel

const val CART_GRAPH = "cart_graph"

data object CartDestination : KiparoPizzaDestination {
    override val route = "$CART_GRAPH/cart"
}

data class CartTopLevelDestination(
    override val iconId: Int = R.drawable.ic_shopping_cart,
    override val titleId: Int = R.string.cart,
    override val badgeValue: Int = 0,
    override val graph: String = CART_GRAPH
) : KiparoTopLevelDestination, KiparoTopLevelDestinationWithCount {
    override fun copyWithNewBadge(value : Int): CartTopLevelDestination {
        return copy(badgeValue = value)
    }
}

fun NavGraphBuilder.cart() {
    navigation(startDestination = CartDestination.route, route = CART_GRAPH){
        composable(route = CartDestination.route) {
            val viewModel: CartViewModel = viewModel(
                factory = CartViewModel.Factory(
                    getCartUseCase = DiProvider.di.get(ObserveCartUseCase::class),
                    removeFromCartUseCase = DiProvider.di.get(RemoveFromCartUseCase::class)
                )
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            CartScreen(
                uiState = uiState,
                onRemoveItemClick = viewModel::removeFromCart,
                onCheckoutClick = viewModel::checkout
            )
        }
    }
}
