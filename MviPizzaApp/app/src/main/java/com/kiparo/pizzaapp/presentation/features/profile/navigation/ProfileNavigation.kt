package com.kiparo.pizzaapp.presentation.features.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestination
import com.kiparo.pizzaapp.presentation.features.profile.ProfileScreen


const val PROFILE_GRAPH = "profile_graph"

data object ProfileDestination : KiparoPizzaDestination {
    override val route = "$PROFILE_GRAPH/profile"
}

data class ProfileTopLevelDestination(
    override val iconId: Int = R.drawable.ic_profile,
    override val titleId: Int = R.string.profile,
    override val graph: String = PROFILE_GRAPH
) : KiparoTopLevelDestination

interface ProfileNavigator{
    fun navigateOnLogout()
}

fun NavGraphBuilder.profile(navigator: ProfileNavigator) {
    navigation(startDestination = ProfileDestination.route, route = PROFILE_GRAPH){
        composable(route = ProfileDestination.route) {
            ProfileScreen(onLogoutClick = navigator::navigateOnLogout)
        }
    }
}