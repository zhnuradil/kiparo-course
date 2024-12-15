package com.kiparo.pizzaapp.presentation.features.promo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.bodyLargeBold
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.design.theme.space40
import com.kiparo.pizzaapp.core.widgets.Promotions
import com.kiparo.pizzaapp.domain.models.Promotion
import com.kiparo.pizzaapp.presentation.features.menu.widgets.ScreenTitle

@Composable
fun PromoScreen(uiState: PromoUiState) {
    Column(
        modifier = Modifier
            .padding(padding16)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        ScreenTitle(
            textResId = R.string.today_offer_screen_title
        )
        Spacer(modifier = Modifier.height(space16))
        Column(
            verticalArrangement = Arrangement.spacedBy(space40),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Promotions(
                promotion = uiState.promotion
            )
            Text(
                text = stringResource(id = R.string.promocode, uiState.promotion.promoCode),
                style = bodyLargeBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PromoPreview() {
    KiparoPizzaAppTheme {
        PromoScreen(
            PromoUiState(
                promotion = Promotion(
                    title = stringResource(id = R.string.promotions_title),
                    offerPromo = stringResource(id = R.string.free_box_of_fries),
                    offerPromoPrice = stringResource(id = R.string.on_all_orders_above, 150),
                    promoCode = "promoKiparo"
                )
            )
        )
    }
}