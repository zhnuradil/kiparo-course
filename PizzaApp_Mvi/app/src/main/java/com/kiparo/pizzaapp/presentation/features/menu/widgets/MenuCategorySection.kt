package com.kiparo.pizzaapp.presentation.features.menu.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil.compose.AsyncImage
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.cornerRadius16
import com.kiparo.pizzaapp.core.design.theme.itemHeight48
import com.kiparo.pizzaapp.core.design.theme.itemHeight64
import com.kiparo.pizzaapp.core.design.theme.itemWidth48
import com.kiparo.pizzaapp.core.design.theme.itemWidth64
import com.kiparo.pizzaapp.core.design.theme.padding8

@Composable
fun MenuCategorySection(
    id: String,
    title: String,
    imageUrl: String,
    contentDescription: String,
    selected: Boolean = false,
    onClick: (String) -> Unit
) {
    val color =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick(id)
        }) {
        Box(
            modifier = Modifier
                .width(itemWidth64)
                .height(itemHeight64)
                .background(
                    shape = RoundedCornerShape(cornerRadius16),
                    color = color
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(itemWidth48)
                    .height(itemHeight48)
                    .background(color = Color.Transparent)
                    .align(Alignment.Center),
                model = imageUrl, contentDescription = contentDescription,
            )
        }
        Text(
            modifier = Modifier.padding(top = padding8),
            text = title, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


/*Previews*/
class ParamsProvider : PreviewParameterProvider<Pair<String, Boolean>> {
    override val values: Sequence<Pair<String, Boolean>> = sequenceOf(
        "All" to true,
        "Some" to false
    )
}

@Preview(showBackground = true)
@Composable
fun MenuSectionPreview(
    @PreviewParameter(ParamsProvider::class) params: Pair<String, Boolean>
) {
    KiparoPizzaAppTheme {
        MenuCategorySection(
            id = params.first,
            title = params.first,
            "https://api.kiparo.ru/pizza/section_all.png",
            contentDescription = "Menu section ${params.first}",
            selected = params.second,
            onClick = {}
        )
    }
}