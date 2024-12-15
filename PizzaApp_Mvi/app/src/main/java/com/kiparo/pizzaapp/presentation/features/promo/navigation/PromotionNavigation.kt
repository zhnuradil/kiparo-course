package com.kiparo.pizzaapp.presentation.features.promo.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestinationWithCount
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CART_GRAPH
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CartDestination
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CartTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.promo.PromoScreen
import com.kiparo.pizzaapp.presentation.features.promo.PromoViewModel

private const val PROMOTION_GRAPH = "promotion_graph"

data object PromotionDestination : KiparoPizzaDestination {
    override val route = "$PROMOTION_GRAPH/promotion"
}

data class PromoTopLevelDestination(
    override val iconId: Int = R.drawable.ic_percent,
    override val titleId: Int = R.string.promotion,
    override val graph: String = PROMOTION_GRAPH
) : KiparoTopLevelDestination


fun NavGraphBuilder.promotion() {
    navigation(startDestination = PromotionDestination.route, route = PROMOTION_GRAPH) {
        composable(route = PromotionDestination.route) {
            val viewModel: PromoViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            PromoScreen(uiState = uiState)
        }
    }
}