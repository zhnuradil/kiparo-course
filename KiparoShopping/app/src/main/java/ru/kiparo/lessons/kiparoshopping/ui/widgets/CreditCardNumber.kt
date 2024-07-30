package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.creditCardNumberStyle
import ru.kiparo.lessons.kiparoshopping.ui.tools.CreditCardVisualTransformation


private const val TEXT_SIZE_FACTOR: Float = 0.99f

@Composable
internal fun CardNumber(value: String, modifier: Modifier = Modifier) {
    val number = value.fill()
    val fontSize = MaterialTheme.typography.creditCardNumberStyle.fontSize
    val textSize = remember { mutableStateOf(fontSize) }

    BasicTextField(
        modifier = modifier,
        value = number,
        onValueChange = { },
        visualTransformation = CreditCardVisualTransformation(CreditCardVisualTransformation.DOUBLE_SPACE),
        textStyle = MaterialTheme.typography.creditCardNumberStyle.copy(
            fontSize = textSize.value,
            textAlign = TextAlign.Center
        ),
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.lineCount > 1) {
                textSize.value *= TEXT_SIZE_FACTOR
            }
        },
        decorationBox = { innerTextField ->
            if (number.isBlank()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.card_placeholder),
                        style = MaterialTheme.typography.creditCardNumberStyle.copy(
                            fontSize = textSize.value,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                innerTextField()
            }
        }
    )
}

private fun String.fill(): String {
    return if (length < CreditCardVisualTransformation.DIGITS_LIMIT) {
        val diff = CreditCardVisualTransformation.DIGITS_LIMIT - length
        this + CreditCardVisualTransformation.SINGLE_SPACE.repeat(diff)
    } else this
}
