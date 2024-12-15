package com.kiparo.pizzaapp.presentation.features.auth.reset

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
import com.kiparo.pizzaapp.core.widgets.LogoTitle
import com.kiparo.pizzaapp.core.widgets.StatusBarInsetsSpacer
import com.kiparo.pizzaapp.core.widgets.SweetBite
import com.kiparo.pizzaapp.core.widgets.TextWithSingleLink

@Composable
fun ResetPasswordScreen(onResetClick: ()->Unit) {
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
            text = stringResource(R.string.reset_password),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))
        EmailFormField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(space56))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = R.string.reset_password, onClick = onResetClick)
        Spacer(modifier = Modifier.height(space24))

        TextWithSingleLink(sentence = stringResource(id = R.string.already_member_login),
            onLinkClicked = {})
    }
}

@Preview
@Composable
fun ResetPasswordScreenPreview() {
    KiparoPizzaAppTheme {
        ResetPasswordScreen(onResetClick = {})
    }
}