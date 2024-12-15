package com.kiparo.pizzaapp.presentation.features.menu

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.domain.models.Promotion
import com.kiparo.pizzaapp.domain.usecases.cart.AddToCartUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuItemsUseCase
import com.kiparo.pizzaapp.domain.usecases.menu.GetMenuSectionsUseCase
import com.kiparo.pizzaapp.domain.usecases.promoution.GetPromotionsUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface MenuUiState {
    data object Loading : MenuUiState

    @Immutable
    data class Success(
        val sections: ImmutableList<MenuSection>,
        val selectedSectionIndex: Int = 0,
        val products: ImmutableList<MenuItem>,
        val promotion: Promotion,
    ): MenuUiState{
        fun selectionTitle():String = sections[selectedSectionIndex].section
    }
}
class MenuViewModel(
    private val getMenuSectionsUseCase: GetMenuSectionsUseCase,
    private val getMenuItemsUseCase: GetMenuItemsUseCase,
    private val getPromotionsUseCase: GetPromotionsUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initMenu()
    }

    fun onSectionSelected(title: String) {
        viewModelScope.launch {
            val items = getMenuItemsUseCase.execute(title)
            _uiState.update { currentState ->
                (currentState as MenuUiState.Success).copy(
                    selectedSectionIndex = currentState.sections.indexOfFirst {
                        it.section == title
                    },
                    products = items.toPersistentList()
                )
            }
        }
    }

    fun addToCart(item:MenuItem){
        viewModelScope.launch {
            addToCartUseCase.execute(item)
        }
    }

    private fun initMenu(){
        viewModelScope.launch {
            val sections = getMenuSectionsUseCase.execute()
            val items = getMenuItemsUseCase.execute(sections.first().section)
            val promotion = getPromotionsUseCase.execute()
            _uiState.update {_ ->
                MenuUiState.Success(
                    sections = sections.toPersistentList(),
                    selectedSectionIndex = 0,
                    products = items.toPersistentList(),
                    promotion = promotion
                )
            }

        }
    }

    internal class Factory(
        private val getMenuSectionsUseCase: GetMenuSectionsUseCase,
        private val getMenuItemsUseCase: GetMenuItemsUseCase,
        private val getPromotionsUseCase: GetPromotionsUseCase,
        private val addToCartUseCase: AddToCartUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MenuViewModel(
                getMenuSectionsUseCase = getMenuSectionsUseCase,
                getMenuItemsUseCase = getMenuItemsUseCase,
                getPromotionsUseCase = getPromotionsUseCase,
                addToCartUseCase = addToCartUseCase
            ) as T
    }

}