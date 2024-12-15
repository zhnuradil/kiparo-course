package com.kiparo.pizzaapp.presentation.features.bottom_menu.navigation

import androidx.navigation.NavHostController
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.presentation.features.menu.navigation.MenuNavigator
import com.kiparo.pizzaapp.presentation.features.profile.navigation.ProfileNavigator
import com.kiparo.pizzaapp.presentation.features.promo.navigation.PromotionDestination

fun NavHostController.menuNavigator(externalNavigator: BottomMenuNavigator): MenuNavigator =
    object : MenuNavigator {
        override fun navigateToPromotion() {
            navigateSingleTopTo(PromotionDestination.route)
        }

        override fun navigateToDetails(id: String) {
            externalNavigator.onNavigateToDetails(id)
        }

        override fun onNavigateUp() {
            externalNavigator.onNavigateUp()
        }
    }

fun NavHostController.profileNavigator(externalNavigator: BottomMenuNavigator): ProfileNavigator =
    object : ProfileNavigator {
        override fun navigateOnLogout() {
            externalNavigator.onLogout()
        }

    }
