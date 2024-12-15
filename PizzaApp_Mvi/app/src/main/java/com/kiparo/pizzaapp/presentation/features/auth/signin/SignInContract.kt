package com.kiparo.pizzaapp.presentation.features.auth.signin

import com.kiparo.pizzaapp.core.base.UnidirectionalViewModel

private const val EMPTY_STRING: String = ""

interface SignInContract : UnidirectionalViewModel<
        SignInContract.State,
        SignInContract.Action,
        SignInContract.Event?> {

    data class State(
        val login: String,
        val loginError: Boolean,
        val password: String,
        val passwordError: Boolean,
    ) {
        companion object {

            fun initial(): State = State(
                login = EMPTY_STRING,
                loginError = false,
                password = EMPTY_STRING,
                passwordError = false,
            )

            fun notAuthenticated(): State = State(
                login = EMPTY_STRING,
                loginError = true,
                password = EMPTY_STRING,
                passwordError = true,
            )
        }
    }

    sealed interface Action {
        data object SignIn : Action
        data object Register : Action
        data object Reset : Action
        data class EmailChange(val email: String) : Action
        data class PasswordChange(val password: String) : Action
    }

    sealed interface Event {
        data object BackPressed : Event
        data object NavigateToRegister : Event
        data object SignedIn : Event
        data object Reset : Event
    }
}
