/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.pizzaapp.presentation.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.kiparo.pizzaapp.core.design.theme.KiparoPizzaAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KiparoPizzaApp()
        }
    }
}

@Composable
fun KiparoPizzaApp() {
    KiparoPizzaAppTheme {
        KiparoPizzaHost()
    }
}

