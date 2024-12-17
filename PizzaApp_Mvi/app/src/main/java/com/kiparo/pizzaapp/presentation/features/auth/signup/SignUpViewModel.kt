import androidx.lifecycle.ViewModel
import com.kiparo.pizzaapp.core.ui.isValidEmail
import com.kiparo.pizzaapp.core.ui.isValidPassword
import com.kiparo.pizzaapp.presentation.features.auth.signup.SignUpContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel : ViewModel(), SignUpContract {

    private val _uiState = MutableStateFlow(SignUpContract.State.initial())
    override val uiState: StateFlow<SignUpContract.State> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<SignUpContract.Event?>(null)
    override val event: StateFlow<SignUpContract.Event?> = _event.asStateFlow()


    override fun onAction(action: SignUpContract.Action) {
        when (action) {
            is SignUpContract.Action.FirstNameChange -> onFirstNameChange(action.firstname)
            is SignUpContract.Action.EmailChange -> onEmailChange(action.email)
            is SignUpContract.Action.PasswordChange -> onPasswordChange(action.password)
            SignUpContract.Action.RegisterClick -> onSignUp()
            SignUpContract.Action.NavigateToSignIn -> onNavigateToSignIn()
        }
    }

    override fun consume() {
        _event.update { null }
    }

    private fun onSignUp() {
        val firstname = uiState.value.firstname
        val email = uiState.value.email
        val password = uiState.value.password

        val firstnameValid = firstname.isNotEmpty()
        val emailValid = email.isNotEmpty() && email.isValidEmail()
        val passwordValid = password.isNotEmpty() && password.isValidPassword()

        if (firstnameValid && emailValid && passwordValid) {
            _event.update { SignUpContract.Event.SignedUp }
        } else {
            _uiState.update { SignUpContract.State.error(emailValid.not(), passwordValid.not()) }
        }
    }

    private fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailError = email.isEmpty() || email.isValidEmail().not()
            )
        }
    }

    private fun onFirstNameChange(name: String) {
        _uiState.update {
            it.copy(firstname = name)
        }
    }

    private fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = password.isEmpty() || password.isValidPassword().not()
            )
        }
    }

    private fun onNavigateToSignIn() {
        _event.update { SignUpContract.Event.NavigateToSignIn }
    }
}