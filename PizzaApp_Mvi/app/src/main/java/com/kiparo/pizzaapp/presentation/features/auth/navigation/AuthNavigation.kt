package com.kiparo.pizzaapp.presentation.features.auth.navigation

import SignUpViewModel
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.kiparo.pizzaapp.presentation.features.auth.signin.SignInContract
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
        val event by viewModel.event.collectAsStateWithLifecycle()

        DisposableEffect(event) {
            when (event) {
                SignInContract.Event.BackPressed -> Unit
                SignInContract.Event.NavigateToRegister -> onNavigateToRegister()
                SignInContract.Event.SignedIn -> onSignedIn()
                SignInContract.Event.Reset -> onNavigateToReset()
                else -> Unit
            }

            onDispose {
                viewModel.consume()
            }
        }

        SignInScreen(
            uiState = uiState,
            onLoginClick = { viewModel.onAction(SignInContract.Action.SignIn) },
            onRegisterClick = { viewModel.onAction(SignInContract.Action.Register) },
            onResetClick = { viewModel.onAction(SignInContract.Action.Reset) },
            onEmailChange = { email -> viewModel.onAction(SignInContract.Action.EmailChange(email)) },
            onPasswordChange = { password ->
                viewModel.onAction(
                    SignInContract.Action.PasswordChange(password)
                )
            },
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