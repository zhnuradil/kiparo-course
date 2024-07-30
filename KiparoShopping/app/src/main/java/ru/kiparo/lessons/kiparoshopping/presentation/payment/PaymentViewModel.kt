package ru.kiparo.lessons.kiparoshopping.presentation.payment

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


private const val CARD_SIZE_LIMIT = 16
private const val CARD_EXPIRE_DATE_LIMIT = 4
private const val CARD_CVC_LIMIT = 3

@Immutable
data class PaymentUiState(
    val name: String,
    val nameError: Boolean,
    val cardNumber: String,
    val cardNumberError: Boolean,
    val cardExpiryDate: String,
    val cardExpiryDateError: Boolean,
    val cvc: String,
    val cvcError: Boolean
)

@Stable
class PaymentViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        PaymentUiState(
            name = "",
            nameError = false,
            cardNumber = "",
            cardNumberError = false,
            cardExpiryDate = "",
            cardExpiryDateError = false,
            cvc = "",
            cvcError = false
        )
    )

    @Stable
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update {
            it.copy(name = value)
        }
    }

    fun onCardNumberChange(value: String) {
        _uiState.update {
            it.copy(cardNumber = value.take(CARD_SIZE_LIMIT))
        }
    }

    fun onExpiryDateChange(value: String) {
        _uiState.update {
            it.copy(cardExpiryDate = value.take(CARD_EXPIRE_DATE_LIMIT))
        }
    }

    fun onCvcChange(value: String) {
        _uiState.update {
            it.copy(cvc = value.take(CARD_CVC_LIMIT))
        }
    }

    fun onSubmit() {
        _uiState.update {
            it.copy(
                nameError = it.name.isBlank(),
                cardNumberError = it.cardNumber.isBlank(),
                cardExpiryDateError = it.cardExpiryDate.isBlank(),
                cvcError = it.cvc.isBlank()
            )
        }
    }
}