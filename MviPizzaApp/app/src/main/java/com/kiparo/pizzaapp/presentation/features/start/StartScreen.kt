package com.kiparo.pizzaapp.presentation.features.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.SystemBarsColorDisposableEffect
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.itemHeight75
import com.kiparo.pizzaapp.core.design.theme.itemWidth104
import com.kiparo.pizzaapp.core.design.theme.itemWidth216
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.design.theme.space32
import com.kiparo.pizzaapp.core.design.theme.space40
import com.kiparo.pizzaapp.core.widgets.BottomBarInsetsSpacer
import com.kiparo.pizzaapp.core.widgets.DefaultButton
import com.kiparo.pizzaapp.core.widgets.EnterImage
import com.kiparo.pizzaapp.core.widgets.LogoSlogan
import com.kiparo.pizzaapp.core.widgets.LogoTitle
import com.kiparo.pizzaapp.core.widgets.StatusBarInsetsSpacer
import com.kiparo.pizzaapp.core.widgets.SweetBite

@Composable
fun StartScreen(modifier: Modifier = Modifier, onGetStarted: () -> Unit) {
    SystemBarsColorDisposableEffect(false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(padding16)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        SweetBite(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )
        LogoTitle()
        Spacer(modifier = Modifier.height(space32))
        EnterImage(imageResId = R.drawable.huge_pizza)
        Spacer(modifier = Modifier.height(space32))
        LogoSlogan()
        Spacer(modifier = modifier.height(space40))
        DefaultButton(
            modifier = Modifier.width(itemWidth216),
            textResId = R.string.get_started,
            onClick = onGetStarted)
        BottomBarInsetsSpacer()
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    KiparoPizzaAppTheme {
        StartScreen(onGetStarted = {})
    }
}