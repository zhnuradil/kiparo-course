/*
 * Copyright (c) 2023. Kiparo.ru
 */

package ru.kiparo.lessons.kiparoshopping.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterPage() {
    val viewModel: RegisterViewModel = viewModel()

    RegisterPage(
        viewModel = viewModel,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onMobileChange = viewModel::onMobileChange,
        onPasswordChanged = viewModel::onPasswordChange,
        onPasswordConfirmChange = viewModel::onPasswordConfirmChange,
        onReferalChange = viewModel::onReferalChange,
        onSubmit = viewModel::register
    )

}

@Composable
internal fun RegisterPage(
    viewModel: RegisterViewModel,
    onNameChange: (value: String) -> Unit,
    onEmailChange: (value: String) -> Unit,
    onMobileChange: (value: String) -> Unit,
    onPasswordChanged: (value: String) -> Unit,
    onPasswordConfirmChange: (value: String) -> Unit,
    onReferalChange: (value: String) -> Unit,
    onSubmit: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreen(
        uiState = uiState,
        onNameChange = onNameChange,
        onEmailChange = onEmailChange,
        onMobileChange = onMobileChange,
        onPasswordChange = onPasswordChanged,
        onPasswordConfirmChange = onPasswordConfirmChange,
        onReferalChange = onReferalChange,
        onSubmit = onSubmit
    )
}