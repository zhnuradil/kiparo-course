package com.kiparo.pizzaapp.presentation.features.start.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.presentation.features.start.StartScreen

private const val ROUTE = "start"


data object StartDestination : KiparoPizzaDestination{
    override val route = ROUTE
}

interface StartNavigator{
    fun onNavigateAfterStarted()
}

fun NavGraphBuilder.start(externalNavigator: StartNavigator){

    composable(route = StartDestination.route){
        StartScreen(onGetStarted = externalNavigator::onNavigateAfterStarted)
    }
}