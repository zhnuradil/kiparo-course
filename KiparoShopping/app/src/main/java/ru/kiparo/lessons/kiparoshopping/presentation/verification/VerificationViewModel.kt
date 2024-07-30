package ru.kiparo.lessons.kiparoshopping.presentation.verification

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val EMPTY_STRING = ""
private const val DIGIT_DEPTH = 1

@Immutable
data class VerificationCodeState(
    val digits: List<String> = listOf(),
    val hasErrors: Boolean = false
) {
    val length: Int = digits.size
    val isBlank: Boolean = digits.all { it.isBlank() }
}

@Immutable
data class VerificationUiState(
    val mobile: String,
    val verificationCodeState: VerificationCodeState
)

class VerificationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        VerificationUiState(
            mobile = "77771112233",
            verificationCodeState = VerificationCodeState(
                digits = listOf(
                    EMPTY_STRING,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    EMPTY_STRING
                )
            )
        )
    )
    val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()

    fun onChange(index: Int, value: String) {
        _uiState.update { currentState ->
            val updatedDigits = currentState.verificationCodeState.digits.toMutableList().apply {
                this[index] = value.takeLast(DIGIT_DEPTH)
            }
            currentState.copy(
                verificationCodeState = currentState.verificationCodeState.copy(
                    digits = updatedDigits
                )
            )
        }
    }

    fun onResend() {
        _uiState.update { currentState ->
            val resetDigits = mutableListOf<String>().apply {
                repeat(times = currentState.verificationCodeState.length) {
                    add(EMPTY_STRING)
                }
            }
            currentState.copy(
                verificationCodeState = currentState.verificationCodeState.copy(
                    digits = resetDigits
                )
            )
        }
    }

    fun onSubmit() {
        _uiState.update { currentState ->
            currentState.copy(
                verificationCodeState = currentState.verificationCodeState.copy(
                    hasErrors = currentState.verificationCodeState.digits.contains(EMPTY_STRING)
                )
            )
        }
    }
}