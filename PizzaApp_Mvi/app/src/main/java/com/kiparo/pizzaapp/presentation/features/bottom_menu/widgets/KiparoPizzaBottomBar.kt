package com.kiparo.pizzaapp.presentation.features.bottom_menu.widgets

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestinationWithCount
import com.kiparo.pizzaapp.presentation.features.bottom_menu.BottomMenuUiState
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CART_GRAPH
import com.kiparo.pizzaapp.presentation.features.cart.navigation.CartTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.menu.navigation.MenuTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.profile.navigation.ProfileTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.promo.navigation.PromoTopLevelDestination
import kotlinx.collections.immutable.persistentListOf


// Say about https://m2.material.io/components/bottom-navigation
// This is a M3 https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#navigationbar

@Composable
fun KiparoPizzaBottomBar(
    modifier: Modifier = Modifier,
    bottomUiState: BottomMenuUiState,
    currentDestination: String?,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {
    KiparoPizzaNavBar {
        bottomUiState.topLevelDestinations.forEachIndexed { index, item ->
            AnimatedIconTab(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                iconResourceId = item.iconId,
                contentDescription = stringResource(item.titleId),
                badgeIndicator = if (item is KiparoTopLevelDestinationWithCount && item.badgeValue > 0)
                    item.badgeValue else null,
                selected = currentDestination == item.graph,
                onClicked = { onNavigateToTopLevel(item.graph) },
                selectedColor = MaterialTheme.colorScheme.primary,
                notSelectedColor = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
fun KiparoNavBarPreview() {
    KiparoPizzaAppTheme {
        KiparoPizzaBottomBar(
            bottomUiState = BottomMenuUiState(
                topLevelDestinations = persistentListOf(
                    MenuTopLevelDestination(),
                    PromoTopLevelDestination(),
                    CartTopLevelDestination(),
                    ProfileTopLevelDestination()
                )
            ),
            currentDestination = CART_GRAPH,
            onNavigateToTopLevel = {},
        )
    }
}