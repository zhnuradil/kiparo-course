package com.kiparo.pizzaapp.presentation.features.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.widgets.GradientButton
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.presentation.features.cart.widgets.CartItem
import com.kiparo.pizzaapp.presentation.features.menu.widgets.ScreenTitle
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen(
    uiState: CartUiState,
    onRemoveItemClick: (String) -> Unit,
    onCheckoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding16),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        Column {
            ScreenTitle(
                textResId = R.string.cart_screen_title,
                modifier = Modifier.padding(start = padding16)
            )
            Spacer(modifier = Modifier.height(space16))

            LazyColumn(
                modifier = Modifier.fillMaxHeight().weight(0.95f),
                verticalArrangement = Arrangement.spacedBy(space16)) {
                items(uiState.cart) { orderedItem ->
                    CartItem(
                        id = orderedItem.id,
                        title = orderedItem.title,
                        price = orderedItem.price,
                        imageUrl = orderedItem.image,
                        onClick = onRemoveItemClick
                    )
                }
            }
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                textResId = R.string.checkout,
                onClick = onCheckoutClick
            )
        }
    }
}

private val productsList = persistentListOf(
    MenuItem(
        section = MenuSection("Pizza", image = "https://api.kiparo.ru/pizza/section_all.png"),
        id = "1",
        title = "Three Toppings Pizza",
        description = "Saba's classic thin crust, New York City pizza.",
        image = "https://api.kiparo.ru/pizza/pizzaa.png",
        price = "25"
    ),
    MenuItem(
        section = MenuSection("Pizza", image = "https://api.kiparo.ru/pizza/section_all.png"),
        id = "2",
        title = "Three Toppings Pizza",
        description = "Saba's classic thin crust, New York City pizza.",
        image = "https://api.kiparo.ru/pizza/pizzaa.png",
        price = "25"
    ),
    MenuItem(
        section = MenuSection("Pizza", image = "https://api.kiparo.ru/pizza/section_all.png"),
        id = "3",
        title = "Three Toppings Pizza",
        description = "Saba's classic thin crust, New York City pizza.",
        image = "https://api.kiparo.ru/pizza/pizzaa.png",
        price = "25"
    ),
    MenuItem(
        section = MenuSection("Pizza", image = "https://api.kiparo.ru/pizza/section_all.png"),
        id = "4",
        title = "Three Toppings Pizza",
        description = "Saba's classic thin crust, New York City pizza.",
        image = "https://api.kiparo.ru/pizza/pizzaa.png",
        price = "25"
    ),
    MenuItem(
        section = MenuSection("Pizza", image = "https://api.kiparo.ru/pizza/section_all.png"),
        id = "5",
        title = "Three Toppings Pizza",
        description = "Saba's classic thin crust, New York City pizza.",
        image = "https://api.kiparo.ru/pizza/pizzaa.png",
        price = "25"
    )
)

@Preview
@Composable
fun CartScreenPreview() {
    KiparoPizzaAppTheme {
        CartScreen(
            uiState = CartUiState(
                productsList
            ),
            onRemoveItemClick = {},
            onCheckoutClick = {}
        )
    }
}