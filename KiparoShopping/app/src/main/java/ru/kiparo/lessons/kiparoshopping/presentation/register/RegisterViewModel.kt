package ru.kiparo.lessons.kiparoshopping.presentation.register

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.kiparo.lessons.kiparoshopping.ui.tools.isValidEmail

data class RegisterUiState(
    val name: String,
    val nameError: Boolean,
    val email: String,
    val emailError: Boolean,
    val password: String,
    val passwordError: Boolean,
    val confirm: String,
    val referal: String,
    val mobile: String,
    val mobileError: Boolean
)

@Stable
class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegisterUiState(
            name = "Sue Daugherty",
            nameError = false,
            email = "shawn.tillman@example.com",
            emailError = false,
            password = "epicurei",
            passwordError = false,
            referal = "deserunt",
            mobile = "234234234234",
            mobileError = false,
            confirm = "proin",
        )
    )

    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onNameChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = value,
                nameError = value.isBlank())
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = email.isNotBlank() && !email.isValidEmail()
            )
        }
    }

    fun onMobileChange(mobile: String) {
        _uiState.update { currentState ->
            currentState.copy(mobile = mobile)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = password.isNotBlank() && password != currentState.confirm
            )
        }
    }

    fun onPasswordConfirmChange(confirmation: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirm = confirmation,
                passwordError = confirmation.isNotBlank() && confirmation != currentState.password
            )
        }
    }

    fun onReferalChange(referal: String) {
        _uiState.update { currentState ->
            currentState.copy(
                referal = referal
            )
        }
    }

    fun register() {
        _uiState.update { currentState ->
            currentState.copy(
                nameError = currentState.name.isBlank(),
                emailError = currentState.email.isBlank(),
                mobileError = currentState.mobile.isBlank(),
                passwordError = currentState.name.isBlank(),
            )
        }
    }

}