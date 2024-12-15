package com.kiparo.pizzaapp.presentation.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.pizzaapp.R
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space16
import com.kiparo.pizzaapp.core.widgets.GradientButton
import com.kiparo.pizzaapp.presentation.features.menu.widgets.ScreenTitle

@Composable
fun ProfileScreen(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(padding16)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        Column {
            ScreenTitle(
                textResId = R.string.profile_screen_title,
                modifier = Modifier.padding(start = padding16)
            )
            Spacer(modifier = Modifier.height(space16))
            Box(modifier = Modifier.weight(1f)) {
                GradientButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    textResId = R.string.logout,
                    onClick = onLogoutClick
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    KiparoPizzaAppTheme {
        ProfileScreen {

        }
    }
}