package com.kiparo.pizzaapp.presentation.features.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.padding8
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.ui.spanned
import com.kiparo.pizzaapp.core.widgets.LoadingIndicator
import com.kiparo.pizzaapp.core.widgets.Promotions
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.domain.models.Promotion
import com.kiparo.pizzaapp.presentation.features.menu.widgets.MenuSections
import com.kiparo.pizzaapp.presentation.features.menu.widgets.ProductItem
import com.kiparo.pizzaapp.presentation.features.menu.widgets.ScreenTitle
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MenuScreen(
    uiState: MenuUiState,
    onSectionSelect: (String) -> Unit,
    onPromotionClick: () -> Unit,
    onItemSelected: (itemId: String) -> Unit,
    onAddToCartClicked: (itemId: MenuItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        Column {
            ScreenTitle(
                textResId = R.string.menu,
                modifier = Modifier.padding(start = padding16)
            )
            Spacer(modifier = Modifier.height(space16))
            when (uiState) {
                is MenuUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                is MenuUiState.Success -> {
                    ScreenContent(
                        uiState,
                        onSectionSelect = onSectionSelect,
                        onPromotionClick = onPromotionClick,
                        onItemSelected = onItemSelected,
                        onAddToCartClicked = onAddToCartClicked
                    )
                }

            }
        }
    }
}

@Composable
private fun ColumnScope.ScreenContent(
    uiState: MenuUiState.Success,
    onSectionSelect: (String) -> Unit,
    onPromotionClick: () -> Unit,
    onItemSelected: (String) -> Unit,
    onAddToCartClicked: (MenuItem) -> Unit
) {
    MenuSections(
        sections = uiState.sections,
        selected = uiState.selectedSectionIndex,
        onSectionSelect = onSectionSelect
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(padding8)
    ) {
        spanned {
            Box(
                modifier = Modifier
                    .padding(padding8)
                    .clickable {
                        onPromotionClick()
                    }
            ) {
                Promotions(
                    modifier = Modifier
                        .fillMaxWidth(),
                    promotion = uiState.promotion
                )
            }
        }
        spanned {
            Box(
                modifier = Modifier
                    .padding(horizontal = padding8)
            ) {
                Text(
                    text = uiState.selectionTitle(),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        items(uiState.products) { menuItem ->
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding8)
            ) {
                ProductItem(
                    item = menuItem,
                    onAddToCartClicked = onAddToCartClicked,
                    onItemClicked = onItemSelected
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    KiparoPizzaAppTheme {
        MenuScreen(uiState = MenuUiState.Success(
            sections = persistentListOf(
                MenuSection("All", image = "https://api.kiparo.ru/pizza/section_all.png"),
                MenuSection("All1", image = "https://api.kiparo.ru/pizza/section_all.png"),
                MenuSection("All2", image = "https://api.kiparo.ru/pizza/section_all.png"),
                MenuSection("All3", image = "https://api.kiparo.ru/pizza/section_all.png"),
                MenuSection("All4", image = "https://api.kiparo.ru/pizza/section_all.png")
            ),
            5,
            products = persistentListOf(
                MenuItem(
                    section = MenuSection(
                        "Pizza",
                        image = "https://api.kiparo.ru/pizza/section_all.png"
                    ),
                    id = "1",
                    title = "Three Toppings Pizza",
                    description = "Saba's classic thin crust, New York City pizza.",
                    image = "https://api.kiparo.ru/pizza/pizzaa.png",
                    price = "25"
                ),
                MenuItem(
                    section = MenuSection(
                        "Pizza",
                        image = "https://api.kiparo.ru/pizza/section_all.png"
                    ),
                    id = "2",
                    title = "Three Toppings Pizza",
                    description = "Saba's classic thin crust, New York City pizza.",
                    image = "https://api.kiparo.ru/pizza/pizzaa.png",
                    price = "25"
                ),
                MenuItem(
                    section = MenuSection(
                        "Pizza",
                        image = "https://api.kiparo.ru/pizza/section_all.png"
                    ),
                    id = "3",
                    title = "Three Toppings Pizza",
                    description = "Saba's classic thin crust, New York City pizza.",
                    image = "https://api.kiparo.ru/pizza/pizzaa.png",
                    price = "25"
                ),
                MenuItem(
                    section = MenuSection(
                        "Pizza",
                        image = "https://api.kiparo.ru/pizza/section_all.png"
                    ),
                    id = "4",
                    title = "Three Toppings Pizza",
                    description = "Saba's classic thin crust, New York City pizza.",
                    image = "https://api.kiparo.ru/pizza/pizzaa.png",
                    price = "25"
                ),
                MenuItem(
                    section = MenuSection(
                        "Pizza",
                        image = "https://api.kiparo.ru/pizza/section_all.png"
                    ),
                    id = "5",
                    title = "Three Toppings Pizza",
                    description = "Saba's classic thin crust, New York City pizza.",
                    image = "https://api.kiparo.ru/pizza/pizzaa.png",
                    price = "25"
                )
            ),
            promotion = Promotion(
                title = stringResource(id = R.string.promotions_title),
                offerPromo = stringResource(id = R.string.free_box_of_fries),
                offerPromoPrice = stringResource(id = R.string.on_all_orders_above, 150),
                promoCode = "promoKiparo"
            ),
        ),
            onSectionSelect = {},
            onPromotionClick = {},
            onItemSelected = {},
            onAddToCartClicked = {})
    }
}