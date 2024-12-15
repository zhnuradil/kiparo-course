package com.kiparo.pizzaapp.presentation.features.promo

import androidx.lifecycle.ViewModel
import com.kiparo.pizzaapp.domain.models.Promotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PromoUiState(
    val promotion: Promotion
)

class PromoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        PromoUiState(
            Promotion(
                title = "Today's Offer",
                offerPromo = "Free Box of {{Fries!}}",
                offerPromoPrice = "on all orders above {{$150}}",
                promoCode = "kiparo2023"
            )
        )
    )
    val uiState = _uiState.asStateFlow()
}