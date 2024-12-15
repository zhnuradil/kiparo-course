package com.kiparo.pizzaapp.presentation.features.auth.navigation

import SignUpViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kiparo.pizzaapp.core.navigation.KiparoPizzaDestination
import com.kiparo.pizzaapp.core.navigation.navigateSingleTopTo
import com.kiparo.pizzaapp.presentation.features.auth.reset.ResetPasswordScreen
import com.kiparo.pizzaapp.presentation.features.auth.reset.ResetPasswordViewModel
import com.kiparo.pizzaapp.presentation.features.auth.signin.SignInScreen
import com.kiparo.pizzaapp.presentation.features.auth.signin.SignInViewModel
import com.kiparo.pizzaapp.presentation.features.auth.signup.SignUpScreen

const val AUTH_ROUTE = "authentication"

fun NavHostController.navigateToAuth() {
    navigateSingleTopTo(AUTH_ROUTE)
}

fun NavHostController.navigateToRegister() {
    navigate(SignUpDestination.route)
}

fun NavHostController.navigateToReset() {
    navigate(ResetPasswordDestination.route)
}


private data object SignInDestination : KiparoPizzaDestination {
    override val route = "$AUTH_ROUTE/signin"
}

private data object SignUpDestination : KiparoPizzaDestination {
    override val route = "$AUTH_ROUTE/signup"
}

private data object ResetPasswordDestination : KiparoPizzaDestination {
    override val route = "$AUTH_ROUTE/resetpassword"
}

interface AuthNavigator {
    fun onNavigateAfterLogin()
    fun onNavigateToRegister()
    fun onNavigateToReset()
    fun onNavigateUp()
}

fun NavGraphBuilder.authentication(externalNavigator: AuthNavigator) {
    navigation(startDestination = SignInDestination.route, route = AUTH_ROUTE) {
        signIn(
            onSignedIn = externalNavigator::onNavigateAfterLogin,
            onNavigateToRegister = externalNavigator::onNavigateToRegister,
            onNavigateToReset = externalNavigator::onNavigateToReset,
        )
        signUp(onRegisterClick = externalNavigator::onNavigateUp)
        resetPassword(onResetClick = externalNavigator::onNavigateUp)
    }
}

private fun NavGraphBuilder.signIn(
    onSignedIn: () -> Unit, onNavigateToRegister: () -> Unit, onNavigateToReset: () -> Unit
) {
    composable(route = SignInDestination.route) {
        val viewModel: SignInViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SignInScreen(
            uiState = uiState,
            onLoginClick = onSignedIn,
            onRegisterClick = onNavigateToRegister,
            onResetClick = onNavigateToReset,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
        )
    }
}

private fun NavGraphBuilder.signUp(onRegisterClick: () -> Unit) {
    composable(route = SignUpDestination.route) {
        val viewModel: SignUpViewModel = viewModel()
        SignUpScreen(
            onRegisterClick = onRegisterClick,
            onFirstNameChange = viewModel::onFirstNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange
        )
    }
}

private fun NavGraphBuilder.resetPassword(
    onResetClick: () -> Unit
) {
    composable(route = ResetPasswordDestination.route) {
        val viewModel: ResetPasswordViewModel = viewModel()
        ResetPasswordScreen(onResetClick)
    }
}