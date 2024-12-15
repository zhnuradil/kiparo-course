package com.kiparo.pizzaapp.presentation.features.auth.signin

import androidx.lifecycle.ViewModel
import com.kiparo.pizzaapp.core.ui.isValidEmail
import com.kiparo.pizzaapp.core.ui.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignInViewModel : ViewModel(), SignInContract {
    private val _uiState = MutableStateFlow(SignInContract.State.initial())
    override val uiState = _uiState.asStateFlow()

    private val _event = MutableStateFlow<SignInContract.Event?>(null)
    override val event = _event.asStateFlow()

    override fun onAction(action: SignInContract.Action) {
        when (action) {
            is SignInContract.Action.EmailChange -> onEmailChange(action.email)
            is SignInContract.Action.PasswordChange -> onPasswordChange(action.password)
            SignInContract.Action.Register -> onRegister()
            SignInContract.Action.Reset -> onReset()
            SignInContract.Action.SignIn -> onSignIn()
        }
    }

    override fun consume() {
        _event.update { null }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                login = email,
                loginError = email.isNotEmpty() && !email.isValidEmail()
            )
        }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = password.isNotEmpty() && password.isValidPassword().not()
            )
        }
    }

    private fun onSignIn() {
        val email = uiState.value.login
        val password = uiState.value.password
        val emailValid = email.isNotEmpty() && email.isValidEmail()
        val passwordValid = password.isNotEmpty() && password.isValidPassword()

        if (emailValid && passwordValid) {
            _event.update {
                SignInContract.Event.SignedIn
            }
        } else {
            _uiState.update {
                SignInContract.State.notAuthenticated()
            }
        }
    }

    private fun onRegister() {
        _event.update { SignInContract.Event.NavigateToRegister }
    }

    private fun onReset() {
        _event.update { SignInContract.Event.Reset }
    }
}
