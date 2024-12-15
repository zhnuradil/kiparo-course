package com.kiparo.pizzaapp.presentation.features.auth.signin

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.itemHeight75
import com.kiparo.pizzaapp.core.design.theme.itemWidth104
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.design.theme.space24
import com.kiparo.pizzaapp.core.design.theme.space32
import com.kiparo.pizzaapp.core.widgets.BackgroundColumn
import com.kiparo.pizzaapp.core.widgets.DefaultButton
import com.kiparo.pizzaapp.core.widgets.FormField
import com.kiparo.pizzaapp.core.widgets.LogoTitle
import com.kiparo.pizzaapp.core.widgets.StatusBarInsetsSpacer
import com.kiparo.pizzaapp.core.widgets.SweetBite
import com.kiparo.pizzaapp.core.widgets.TextWithSingleLink

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onResetClick: () -> Unit
) {
    BackgroundColumn {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        SweetBite(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )
        LogoTitle()
        Spacer(modifier = Modifier.height(space32))

        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.login,
            placeholderResId = R.string.email_placeholder,
            leadingIconResId = R.drawable.ic_mail,
            hasError = uiState.loginError,
            onValueChange = onEmailChange,
        )
        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            placeholderResId = R.string.password_placeholder,
            leadingIconResId = R.drawable.ic_key,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(space32))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = R.string.login,
            onClick = onLoginClick
        )
        Spacer(modifier = Modifier.height(space24))

        TextWithSingleLink(
            sentence = stringResource(id = R.string.not_a_member_register),
            onLinkClicked = onRegisterClick
        )
        Spacer(modifier = Modifier.height(space16))

        TextWithSingleLink(
            sentence = stringResource(R.string.forgot_password_reset),
            onLinkClicked = onResetClick
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    KiparoPizzaAppTheme {
        SignInScreen(uiState = SignInUiState(
            login = "", loginError = true,
            password = ""),
            onLoginClick = {}, onRegisterClick = {}, onResetClick = {},
            onEmailChange = {},
            onPasswordChange = {})
    }
}