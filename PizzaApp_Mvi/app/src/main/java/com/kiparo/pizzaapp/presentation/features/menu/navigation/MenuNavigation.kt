package com.kiparo.pizzaapp.presentation.features.menu.navigation

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
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuItemsUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuSectionsUseCase
import com.kiparo.pizzaapp.domain.usecases.promoution.GetPromotionsUseCase
import com.kiparo.pizzaapp.presentation.features.menu.MenuScreen
import com.kiparo.pizzaapp.presentation.features.menu.MenuViewModel

const val MENU_GRAPH = "menu_graph"

data class MenuTopLevelDestination(
    override val iconId: Int = R.drawable.ic_home,
    override val titleId: Int = R.string.menu,
    override val graph: String = MENU_GRAPH
) : KiparoTopLevelDestination

data object MenuDestination : KiparoPizzaDestination {
    override val route = "$MENU_GRAPH/menu"
}

interface MenuNavigator{
    fun navigateToPromotion()
    fun navigateToDetails(id:String)
    fun onNavigateUp()
}

fun NavGraphBuilder.menu(externalNavigator: MenuNavigator) {

    navigation(startDestination = MenuDestination.route, route = MENU_GRAPH) {
        composable(route = MenuDestination.route) {
            val viewModel: MenuViewModel = viewModel(
                factory = MenuViewModel.Factory(
                    getMenuItemsUseCase = DiProvider.di.get(GetMenuItemsUseCase::class),
                    getMenuSectionsUseCase = DiProvider.di.get(GetMenuSectionsUseCase::class),
                    getPromotionsUseCase = DiProvider.di.get(GetPromotionsUseCase::class),
                    addToCartUseCase = DiProvider.di.get(AddToCartUseCase::class)
                )
            )

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            MenuScreen(
                uiState = uiState,
                onSectionSelect = viewModel::onSectionSelected,
                onPromotionClick = externalNavigator::navigateToPromotion,
                onItemSelected = externalNavigator::navigateToDetails,
                onAddToCartClicked = viewModel::addToCart
            )
        }
        // This is an example of the promo as an inner page
        // promotion()
    }

}