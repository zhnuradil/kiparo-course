package com.kiparo.pizzaapp.core.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.kiparo.pizzaapp.R

@Composable
fun PasswordFormField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    FormField(
        modifier = modifier,
        value = value,
        placeholderResId = R.string.password_placeholder,
        leadingIconResId = R.drawable.ic_key,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = onValueChange
    )
}