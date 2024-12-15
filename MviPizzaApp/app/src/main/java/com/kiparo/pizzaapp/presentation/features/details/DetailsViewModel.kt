package com.kiparo.pizzaapp.presentation.features.details

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetSingleMenuItemUseCase
import com.kiparo.pizzaapp.presentation.features.details.navigation.DetailsDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(val menuItem: MenuItem) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}

class DetailsViewModel(
    private val getSingleMenuItemUseCase: GetSingleMenuItemUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val menuItemId: String =
        savedStateHandle[DetailsDestination.argumentName]!!

    private val _uiState = MutableStateFlow<DetailsUiState>(
        DetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val menuItem = getSingleMenuItemUseCase.execute(menuItemId)
            _uiState.update {
                DetailsUiState.Success(menuItem)
            }
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            addToCartUseCase.execute(menuItem =
            (uiState.value as DetailsUiState.Success).menuItem)
        }
    }

    internal class Factory(
        private val getSingleMenuItemUseCase: GetSingleMenuItemUseCase,
        private val addToCartUseCase: AddToCartUseCase,
    ) : AbstractSavedStateViewModelFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return DetailsViewModel(
                getSingleMenuItemUseCase = getSingleMenuItemUseCase,
                addToCartUseCase = addToCartUseCase,
                savedStateHandle = handle
            ) as T
        }
    }

}