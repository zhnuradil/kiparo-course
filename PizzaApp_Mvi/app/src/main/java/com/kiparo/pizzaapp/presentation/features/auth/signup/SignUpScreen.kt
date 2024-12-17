package com.kiparo.pizzaapp.presentation.features.auth.signup

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.itemHeight75
import com.kiparo.pizzaapp.core.design.theme.itemWidth104
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.design.theme.space24
import com.kiparo.pizzaapp.core.design.theme.space32
import com.kiparo.pizzaapp.core.design.theme.space56
import com.kiparo.pizzaapp.core.widgets.BackgroundColumn
import com.kiparo.pizzaapp.core.widgets.DefaultButton
import com.kiparo.pizzaapp.core.widgets.EmailFormField
import com.kiparo.pizzaapp.core.widgets.FormField
import com.kiparo.pizzaapp.core.widgets.LogoTitle
import com.kiparo.pizzaapp.core.widgets.PasswordFormField
import com.kiparo.pizzaapp.core.widgets.StatusBarInsetsSpacer
import com.kiparo.pizzaapp.core.widgets.SweetBite
import com.kiparo.pizzaapp.core.widgets.TextWithSingleLink

@Composable
fun SignUpScreen(
    uiState: SignUpContract.State,
    onFirstNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onNavigateToSignInClick: () -> Unit
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
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))
        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.firstname,
            placeholderResId = R.string.name_placeholder,
            leadingIconResId = R.drawable.ic_person,
            onValueChange = onFirstNameChange
        )
        Spacer(modifier = Modifier.height(space24))
        EmailFormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            hasError = uiState.emailError,
            onValueChange = onEmailChange
        )
        Spacer(modifier = Modifier.height(space24))

        PasswordFormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            hasError = uiState.passwordError,
            onValueChange = onPasswordChange
        )
        Spacer(modifier = Modifier.height(space56))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = R.string.signup, onClick = onRegisterClick
        )
        Spacer(modifier = Modifier.height(space24))

        TextWithSingleLink(sentence = stringResource(R.string.already_member_login),
            onLinkClicked = { onNavigateToSignInClick() })
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    KiparoPizzaAppTheme {
        SignUpScreen(
            uiState = SignUpContract.State.initial(),
            onRegisterClick = {},
            onFirstNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToSignInClick = {}
        )
    }
}