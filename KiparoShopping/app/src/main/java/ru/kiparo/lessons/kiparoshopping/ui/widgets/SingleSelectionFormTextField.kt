package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import ru.kiparo.lessons.kiparoshopping.ui.theme.inputLabelStyle
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_12
import ru.kiparo.lessons.kiparoshopping.ui.theme.zero_val

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSelectionFormTextField(
    modifier: Modifier,
    value: String,
    label: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    onChange: (value: String) -> Unit,
    onKeyEvent: (keyEvent: KeyEvent) -> Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    labelStyle: TextStyle = MaterialTheme.typography.inputLabelStyle,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    contentPadding: PaddingValues = PaddingValues(all = padding_12),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val dynamicColor = if (value.isBlank()) {
        MaterialTheme.colorScheme.onSecondary
    } else {
        MaterialTheme.colorScheme.inverseSurface
    }

    Column {
        label?.let {
            Text(
                text = it,
                style = labelStyle,
                modifier = Modifier.padding(bottom = padding_12),
                color = if (isFocused) {
                    MaterialTheme.colorScheme.inverseSurface
                } else {
                    MaterialTheme.colorScheme.onSecondary
                }
            )
        }

        BasicTextField(
            value = TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            ),
            onValueChange = { textFieldValue ->
                onChange(textFieldValue.text)
            },
            interactionSource = interactionSource,
            modifier = modifier.onKeyEvent { onKeyEvent(it) },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = textStyle.copy(color = dynamicColor),
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    contentPadding = contentPadding,
                    container = {
                        TextFieldDefaults.OutlinedBorderContainerBox(
                            enabled = enabled,
                            isError = isError,
                            interactionSource = interactionSource,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = dynamicColor,
                                unfocusedBorderColor = dynamicColor
                            ),
                            shape = RoundedCornerShape(zero_val)
                        )
                    }
                )
            }
        )
    }
}