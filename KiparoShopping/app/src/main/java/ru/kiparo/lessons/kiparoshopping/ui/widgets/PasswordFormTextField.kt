package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme

@Composable
fun PasswordFormTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onChange: (String) -> Unit,
) {

    var isVisible by remember { mutableStateOf(false) }

    val visibilityIcon by remember {
        derivedStateOf {
            if (isVisible) R.drawable.ic_visible_off
            else R.drawable.ic_visibile_on
        }
    }

    val transformation by remember {
        derivedStateOf {
            if (isVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        }
    }

    FormTextField(
        value = value,
        label = label,
        isError = isError,
        visualTransformation = transformation,
        trailingIcon = {
            Icon(
                painter = painterResource(id = visibilityIcon),
                contentDescription = stringResource(id = R.string.password_visibility),
                modifier = Modifier.clickable(onClick = {
                    isVisible = !isVisible
                }),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        },
        modifier = modifier,
        onChange = onChange
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordFormTextFieldPreview() {
    KiparoShoppingTheme {
        PasswordFormTextField(
            value = "password",
            label = stringResource(id = R.string.password),
            onChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}