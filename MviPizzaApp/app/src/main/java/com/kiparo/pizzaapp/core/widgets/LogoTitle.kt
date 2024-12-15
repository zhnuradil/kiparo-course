package com.kiparo.pizzaapp.core.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.defaultCapsSpanStyle
import com.kiparo.pizzaapp.core.design.theme.highlightSpanStyle

@Composable
fun LogoTitle(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = buildAnnotatedString {
            withStyle(
                style = highlightSpanStyle
            ) {
                append("K")
            }
            withStyle(
                style = defaultCapsSpanStyle
            ) {
                append("iparo")
            }
            withStyle(
                style = highlightSpanStyle
            ) {
                append("P")
            }
            withStyle(
                style = defaultCapsSpanStyle
            ) {
                append("izza")
            }
        }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun LogoTitlePreview() {
    KiparoPizzaAppTheme {
        LogoTitle()
    }
}