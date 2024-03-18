/*
 * Copyright 2023  Kiparo.ru course materials
 */

package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.inputLabelStyle
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_12
import ru.kiparo.lessons.kiparoshopping.ui.theme.zero_val

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (value: String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    labelStyle: TextStyle = MaterialTheme.typography.inputLabelStyle,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column {
        Text(
            text = label,
            style = labelStyle,
            modifier = Modifier.padding(bottom = padding_12),
            color = if (isFocused) MaterialTheme.colorScheme.inverseSurface
            else MaterialTheme.colorScheme.onSecondary
        )

        BasicTextField(
            value = value,
            onValueChange = onChange,
            interactionSource = interactionSource,
            modifier = modifier,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = TextStyle(
                color = if (isFocused) MaterialTheme.colorScheme.inverseSurface
                else MaterialTheme.colorScheme.onSecondary
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = singleLine,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    placeholder = {
                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium
                                .copy(color = Color.LightGray),
                            textAlign = TextAlign.Center
                        )
                    },
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(all = padding_12),
                    container = {
                        TextFieldDefaults.OutlinedBorderContainerBox(
                            enabled = enabled,
                            isError = isError,
                            interactionSource,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.inverseSurface,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary
                            ),
                            shape = RoundedCornerShape(zero_val)
                        )
                    }
                )
            }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormTextFieldPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FormTextField(
            value = "",
            label = stringResource(id = R.string.name_field_title),
            placeholder = stringResource(id = R.string.name_field_placeholder),
            onChange = {}
        )
    }

}