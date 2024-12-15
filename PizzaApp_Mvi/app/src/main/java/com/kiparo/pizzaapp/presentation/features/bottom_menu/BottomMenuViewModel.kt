package com.kiparo.pizzaapp.presentation.features.bottom_menu

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestination
import com.kiparo.pizzaapp.core.navigation.KiparoTopLevelDestinationWithCount
import com.kiparo.pizzaapp.core.navigation.TopDestinationsCollection
import com.kiparo.pizzaapp.domain.usecases.cart.ObserveCartUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class BottomMenuUiState(
    val topLevelDestinations: ImmutableList<KiparoTopLevelDestination>
)

class BottomMenuViewModel(
    private val getCartUseCase: ObserveCartUseCase,
    private val topLevelDestinations: TopDestinationsCollection
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BottomMenuUiState(
            topLevelDestinations = topLevelDestinations.items.toPersistentList()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCartUseCase.execute().collect { itemsInCart ->
                _uiState.update { currentState ->
                    currentState.copy(
                        topLevelDestinations = currentState.topLevelDestinations.toMutableList()
                            .map { destination ->
                                if (destination is KiparoTopLevelDestinationWithCount) {
                                    destination.copyWithNewBadge(itemsInCart.size)
                                } else
                                    destination
                            }.toPersistentList()
                    )
                }
            }
        }
    }


    internal class Factory(
        private val getCartUseCase: ObserveCartUseCase,
        private val topLevelDestinationsCollection: TopDestinationsCollection,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            BottomMenuViewModel(
                getCartUseCase = getCartUseCase,
                topLevelDestinations = topLevelDestinationsCollection
            ) as T
    }
}