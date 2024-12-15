package com.kiparo.pizzaapp.presentation.features.auth.signin

import androidx.lifecycle.ViewModel
import com.kiparo.pizzaapp.core.ui.isValidEmail
import com.kiparo.pizzaapp.core.ui.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class SignInUiState(
    val login: String,
    val loginError: Boolean,
    val password: String,
)

class SignInViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        SignInUiState(
            login = "",
            loginError = false,
            password = ""
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                login = email,
                loginError = email.isNotEmpty() && !email.isValidEmail()
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }
}