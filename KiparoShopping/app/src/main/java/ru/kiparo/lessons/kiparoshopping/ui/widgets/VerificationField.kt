package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.kiparo.lessons.kiparoshopping.presentation.verification.VerificationCodeState
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_45
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_width_45
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_10
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_16
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_10
import ru.kiparo.lessons.kiparoshopping.ui.theme.verificationCode
import ru.kiparo.lessons.kiparoshopping.ui.tools.onKeyEvent

@Composable
fun VerificationField(
    verificationCodeState: VerificationCodeState,
    onChange: (Int, String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(space_10)) {
        val focusManager = LocalFocusManager.current
        val fieldsCount = verificationCodeState.length

        val requesters = remember {
            mutableStateListOf<FocusRequester>().apply {
                repeat(fieldsCount) {
                    add(element = FocusRequester())
                }
            }
        }

        LaunchedEffect(Unit) {
            if (requesters.size > 1) {
                requesters[0].requestFocus()
            }
        }

        SideEffect {
            if (verificationCodeState.isBlank) {
                requesters[0].requestFocus()
            }
        }

        repeat(fieldsCount) { index ->
            SingleSelectionFormTextField(
                modifier = Modifier
                    .width(item_width_45)
                    .height(item_height_45)
                    .focusRequester(requesters[index]),
                value = verificationCodeState.digits[index],
                onChange = { value ->
                    onChange(index, value)
                },
                onKeyEvent = { key ->
                    focusManager.onKeyEvent(key = key, index = index, limit = fieldsCount)
                },
                isError = verificationCodeState.hasErrors,
                textStyle = MaterialTheme.typography.verificationCode,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                contentPadding = PaddingValues(horizontal = padding_16, vertical = padding_10)
            )
        }
    }
}