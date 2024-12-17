package com.kiparo.pizzaapp.presentation.features.auth.signup

import com.kiparo.pizzaapp.core.base.UnidirectionalViewModel

private const val EMPTY_STRING: String = ""

interface SignUpContract : UnidirectionalViewModel<
        SignUpContract.State,
        SignUpContract.Action,
        SignUpContract.Event?> {

    data class State(
        val firstname: String,
        val email: String,
        val emailError: Boolean,
        val password: String,
        val passwordError: Boolean,
    ) {

        companion object {

            fun initial(): State = State(
                firstname = EMPTY_STRING,
                email = EMPTY_STRING,
                emailError = false,
                password = EMPTY_STRING,
                passwordError = false
            )

            fun error(emailError: Boolean, passwordError: Boolean): State = State(
                firstname = EMPTY_STRING,
                email = EMPTY_STRING,
                emailError = emailError,
                password = EMPTY_STRING,
                passwordError = passwordError
            )
        }
    }

    sealed interface Action {
        data class FirstNameChange(val firstname: String) : Action
        data class EmailChange(val email: String) : Action
        data class PasswordChange(val password: String) : Action
        data object RegisterClick : Action
        data object NavigateToSignIn : Action
    }

    sealed interface Event {
        data object NavigateToSignIn : Event
        data object SignedUp : Event
    }
}