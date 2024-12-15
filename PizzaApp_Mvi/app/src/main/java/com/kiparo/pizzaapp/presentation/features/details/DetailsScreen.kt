package com.kiparo.pizzaapp.presentation.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.SystemBarsColorDisposableEffect
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.defaultShadow
import com.kiparo.pizzaapp.core.design.theme.oneCornerRoundedShape
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.design.theme.space40
import com.kiparo.pizzaapp.core.widgets.GradientButton
import com.kiparo.pizzaapp.core.widgets.LoadingIndicator
import com.kiparo.pizzaapp.core.widgets.PriceText
import com.kiparo.pizzaapp.core.widgets.StatusBarInsetsSpacer
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection

@Composable
fun DetailsScreen(uiState: DetailsUiState, addToCart: () -> Unit, onNavigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        IconButton(
            onClick = onNavigateUp
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Navigate Back",
                tint = MaterialTheme.colorScheme.background
            )
        }

        when(uiState){
            is DetailsUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator()
                }
            }
            is DetailsUiState.Error -> {}
            is DetailsUiState.Success -> {
                Box(
                    modifier = Modifier
                        .padding(horizontal = padding16)
                        .weight(1f)
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = uiState.menuItem.image, contentDescription = uiState.menuItem.title
                    )
                }
                DetailsBlock(uiState = uiState, addToCart = addToCart)
            }
        }

        SystemBarsColorDisposableEffect(putInDark = false)
    }

}

@Composable
private fun ColumnScope.DetailsBlock(uiState: DetailsUiState.Success, addToCart: () -> Unit){
    Box(
        modifier = Modifier
            .defaultShadow()
            .fillMaxWidth()
            .weight(1f)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = oneCornerRoundedShape
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = padding16)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(space40))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = uiState.menuItem.title, style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                PriceText(value = uiState.menuItem.price)
            }
            Spacer(modifier = Modifier.height(padding16))
            Text(
                text = uiState.menuItem.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(space40))
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                textResId = R.string.add_to_cart, onClick = addToCart)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    KiparoPizzaAppTheme {
        DetailsScreen(uiState = DetailsUiState.Success(
            menuItem = MenuItem(
                section = MenuSection(
                    "Pizza",
                    image = "https://api.kiparo.ru/pizza/section_all.png"
                ),
                id = "5",
                title = "Three Toppings Pizza",
                description = "Saba's classic thin crust, New York City pizza.",
                image = "https://api.kiparo.ru/pizza/pizzaa.png",
                price = "25"
            ),
        ), onNavigateUp = {}, addToCart = {})
    }
}
