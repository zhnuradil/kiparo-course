package com.kiparo.pizzaapp.core.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kiparo.pizzaapp.R

@Composable
fun EmailFormField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange:(String)->Unit
) {
    FormField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholderResId = R.string.email_placeholder,
        leadingIconResId = R.drawable.ic_mail
    )
}