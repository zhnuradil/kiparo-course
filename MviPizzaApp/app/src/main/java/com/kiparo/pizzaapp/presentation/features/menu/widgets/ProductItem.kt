package com.kiparo.pizzaapp.presentation.features.menu.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.cornerRadius16
import com.kiparo.pizzaapp.core.design.theme.itemHeight96
import com.kiparo.pizzaapp.core.design.theme.itemWidth140
import com.kiparo.pizzaapp.core.design.theme.padding8
import com.kiparo.pizzaapp.core.widgets.PriceText
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    item: MenuItem,
    onItemClicked: (itemId: String) -> Unit,
    onAddToCartClicked: (MenuItem) -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = { onItemClicked(item.id) })
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = cornerRadius16)
            )
    ) {
        Column(
            modifier = Modifier.padding(padding8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(itemWidth140)
                    .height(itemHeight96)
                    .background(color = Color.Transparent),
                model = item.image, contentDescription = item.title,
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PriceText(value = item.price)
                Image(
                    modifier = Modifier.clickable(onClick = {
                        onAddToCartClicked(item)
                    }),
                    painter = painterResource(R.drawable.ic_add_circle),
                    contentDescription = "Add to cart ${item.title}"
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        item = MenuItem(
            id = "123",
            image = "https://api.kiparo.ru/pizza/section_all.png",
            title = "Pepperony",
            price = "128",
            section = MenuSection(
                section = "inimicus",
                image = "consectetur"
            ),
            description = "dignissim",
        ),
        onAddToCartClicked = {},
        onItemClicked = {}
    )
}