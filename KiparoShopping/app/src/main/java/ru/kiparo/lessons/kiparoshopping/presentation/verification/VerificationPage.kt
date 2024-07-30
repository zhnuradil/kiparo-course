package ru.kiparo.lessons.kiparoshopping.presentation.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VerificationPage() {
    val viewModel: VerificationViewModel = viewModel()

    VerificationPage(
        viewModel = viewModel,
        onCodeChange = viewModel::onChange,
        onResend = viewModel::onResend,
        onSubmit = viewModel::onSubmit
    )
}

@Composable
internal fun VerificationPage(
    viewModel: VerificationViewModel,
    onCodeChange: (Int, String) -> Unit,
    onResend: () -> Unit,
    onSubmit: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    VerificationScreen(
        uiState = uiState,
        onCodeChange = onCodeChange,
        onResend = onResend,
        onSubmit = onSubmit
    )
}