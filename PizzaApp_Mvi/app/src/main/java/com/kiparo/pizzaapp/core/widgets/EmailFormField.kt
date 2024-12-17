package com.kiparo.pizzaapp.core.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kiparo.pizzaapp.R

@Composable
fun EmailFormField(
    modifier: Modifier = Modifier,
    value: String,
    hasError: Boolean,
    onValueChange: (String) -> Unit
) {
    FormField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        hasError = hasError,
        placeholderResId = R.string.email_placeholder,
        leadingIconResId = R.drawable.ic_mail
    )
}