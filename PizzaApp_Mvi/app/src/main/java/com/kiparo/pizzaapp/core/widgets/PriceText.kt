package com.kiparo.pizzaapp.core.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kiparo.pizzaapp.R

@Composable
fun PriceText(value: String) {
    Text(text = stringResource(id = R.string.usd, value),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary)
}