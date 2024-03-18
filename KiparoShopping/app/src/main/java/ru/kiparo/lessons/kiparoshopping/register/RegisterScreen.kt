package ru.kiparo.lessons.kiparoshopping.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_36
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_40
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_24
import ru.kiparo.lessons.kiparoshopping.ui.tools.PhoneVisualTransformation
import ru.kiparo.lessons.kiparoshopping.ui.widgets.DefaultButton
import ru.kiparo.lessons.kiparoshopping.ui.widgets.FormTextField
import ru.kiparo.lessons.kiparoshopping.ui.widgets.PasswordFormTextField
import ru.kiparo.lessons.kiparoshopping.ui.widgets.TopBar

//TODO: Implement according to design
//https://www.figma.com/file/zBSqR7FXWYiPjpQzOk1Bh4/Kiparo-ShoppingApp?type=design&node-id=682%3A87&mode=design&t=9uEG17dJttPlpODc-1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onMobileChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onReferalChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column {
        TopBar(
            title = stringResource(id = R.string.register_screen_title)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding_36, end = padding_36)
                .verticalScroll(rememberScrollState())
                .padding(top = padding_40),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space_24)
        ) {

            val modifier = Modifier.fillMaxWidth()

            FormTextField(
                value = uiState.name,
                label = stringResource(id = R.string.name_field_title),
                isError = uiState.nameError,
                modifier = modifier,
                onChange = onNameChange
            )

            FormTextField(
                value = uiState.email,
                label = stringResource(id = R.string.email_address),
                isError = uiState.emailError,
                modifier = modifier,
                onChange = onEmailChange
            )

            FormTextField(
                value = uiState.mobile,
                label = stringResource(id = R.string.mobile_number),
                isError = uiState.mobileError,
                visualTransformation = PhoneVisualTransformation(
                    PhoneVisualTransformation.RU_MASK,
                    PhoneVisualTransformation.RU_CHAR
                ),
                modifier = modifier,
                onChange = onMobileChange
            )

            PasswordFormTextField(
                value = uiState.password,
                label = stringResource(id = R.string.password),
                isError = uiState.passwordError,
                modifier = modifier,
                onChange = onPasswordChange
            )

            PasswordFormTextField(
                value = uiState.confirm,
                label = stringResource(id = R.string.confirm_password),
                isError = uiState.passwordError,
                modifier = modifier,
                onChange = onPasswordConfirmChange
            )

            FormTextField(
                value = uiState.referal,
                label = stringResource(id = R.string.referal_code),
                modifier = modifier,
                onChange = onReferalChange
            )

            DefaultButton(
                title = stringResource(R.string.register_screen_title),
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KiparoShoppingTheme {
//        RegisterScreen()
    }
}
