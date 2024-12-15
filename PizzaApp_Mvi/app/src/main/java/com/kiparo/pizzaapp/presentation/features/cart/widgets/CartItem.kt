package com.kiparo.pizzaapp.presentation.features.cart.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.bodyLargeBold
import com.kiparo.pizzaapp.core.design.theme.cornerRadius8
import com.kiparo.pizzaapp.core.design.theme.itemHeight96
import com.kiparo.pizzaapp.core.design.theme.itemSize136
import com.kiparo.pizzaapp.core.design.theme.itemWidth140
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space8

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    price: String,
    imageUrl: String,
    onClick: (String) -> Unit
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(end = padding16)
                .height(itemSize136)
                .width(itemSize136)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = cornerRadius8)
                )
        ) {
            AsyncImage(
                modifier =
                Modifier
                    .align(Alignment.Center)
                    .width(itemWidth140)
                    .height(itemHeight96),
                model = imageUrl,
                contentDescription = title
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(space8)
        ) {
            Text(
                text = title, style = bodyLargeBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.usd, price),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Image(
            modifier = Modifier.clickable(
                onClick = { onClick(id) },
            ),
            painter = painterResource(R.drawable.ic_cancel),
            contentDescription = "Remove $title from the cart"
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    KiparoPizzaAppTheme {
        CartItem(
            modifier = Modifier.fillMaxWidth(),
            id = "1",
            title = "perpetua perpetua perpetua perpetua perpetua perpetua" +
                    "perpetua perpetua perpetua ",
            price = "78",
            imageUrl = "https://api.kiparo.ru/pizza/pizzaa.png",
            onClick = {}
        )
    }
}