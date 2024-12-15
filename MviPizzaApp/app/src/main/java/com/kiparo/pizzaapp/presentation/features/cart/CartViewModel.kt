package com.kiparo.pizzaapp.presentation.features.cart

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import com.kiparo.pizzaapp.domain.usecases.cart.RemoveFromCartUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@Immutable
data class CartUiState(val cart: ImmutableList<MenuItem>)

class CartViewModel(
    private val getCartUseCase: ObserveCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState(persistentListOf()))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCartUseCase.execute().collect{ items ->
                _uiState.update {
                    it.copy(
                        cart = items.toPersistentList()
                    )
                }
            }
        }
    }


    fun removeFromCart(id: String) {
        viewModelScope.launch {
            removeFromCartUseCase.execute(id)
        }
    }

    fun checkout() {
        Log.i("Cart", "Checking out %)")
    }

    internal class Factory(
        private val getCartUseCase: ObserveCartUseCase,
        private val removeFromCartUseCase: RemoveFromCartUseCase,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CartViewModel(
                getCartUseCase = getCartUseCase,
                removeFromCartUseCase = removeFromCartUseCase
            ) as T
    }

}
