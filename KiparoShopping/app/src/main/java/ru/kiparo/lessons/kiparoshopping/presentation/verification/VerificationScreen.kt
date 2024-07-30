package ru.kiparo.lessons.kiparoshopping.presentation.verification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_36
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_36
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_50
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_24
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_28
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_40
import ru.kiparo.lessons.kiparoshopping.ui.tools.PhoneVisualTransformation
import ru.kiparo.lessons.kiparoshopping.ui.widgets.DefaultButton
import ru.kiparo.lessons.kiparoshopping.ui.widgets.ResendCodeBlock
import ru.kiparo.lessons.kiparoshopping.ui.widgets.TopBar
import ru.kiparo.lessons.kiparoshopping.ui.widgets.VerificationField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    uiState: VerificationUiState,
    onCodeChange: (Int, String) -> Unit,
    onResend: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            title = stringResource(id = R.string.verification_screen_title),
            onBackClick = { }
        )
        Spacer(modifier = Modifier.height(item_height_36))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = padding_36)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = padding_50),
                text = stringResource(id = R.string.send_code_to_mobile),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(space_28))

            val phoneTransformation = remember {
                PhoneVisualTransformation(
                    mask = PhoneVisualTransformation.RU_MASK,
                    maskCharacter = PhoneVisualTransformation.RU_CHAR
                )
            }

            Text(
                text = stringResource(
                    id = R.string.sent_to,
                    phoneTransformation.applyTo("77011112233")
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(space_24))

            VerificationField(
                verificationCodeState = uiState.verificationCodeState,
                onChange = onCodeChange
            )

            Spacer(modifier = Modifier.height(space_40))

            ResendCodeBlock(onResend)

            Spacer(modifier = Modifier.height(space_40))

            DefaultButton(
                title = stringResource(id = R.string.verify),
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VerificationScreenPreview() {
    KiparoShoppingTheme {
        VerificationScreen(
            uiState = VerificationUiState(
                mobile = "77771112233",
                verificationCodeState = VerificationCodeState(
                    digits = listOf("1", "2", "3", "4"),
                    hasErrors = false
                )
            ),
            onCodeChange = { index, value -> },
            onResend = {},
            onSubmit = {}
        )
    }
}