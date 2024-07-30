package ru.kiparo.lessons.kiparoshopping.presentation.payment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PaymentPage() {
    val paymentViewModel: PaymentViewModel = viewModel()

    PaymentPage(
        paymentViewModel = paymentViewModel,
        onNameChanged = paymentViewModel::onNameChange,
        onCardNumberChanged = paymentViewModel::onCardNumberChange,
        onExpiryDateChanged = paymentViewModel::onExpiryDateChange,
        onCvcChanged = paymentViewModel::onCvcChange,
        onSubmit = paymentViewModel::onSubmit
    )
}

@Composable
internal fun PaymentPage(
    paymentViewModel: PaymentViewModel,
    onNameChanged: (String) -> Unit,
    onCardNumberChanged: (String) -> Unit,
    onExpiryDateChanged: (String) -> Unit,
    onCvcChanged: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val state by paymentViewModel.uiState.collectAsStateWithLifecycle()

    PaymentScreen(
        uiState = state,
        onNameChanged = onNameChanged,
        onCardNumberChanged = onCardNumberChanged,
        onExpiryDateChanged = onExpiryDateChanged,
        onCvcChanged = onCvcChanged,
        onSubmit = onSubmit,
    )
}