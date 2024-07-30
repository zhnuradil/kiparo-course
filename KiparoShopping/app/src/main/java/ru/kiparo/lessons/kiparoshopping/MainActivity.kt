package ru.kiparo.lessons.kiparoshopping

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.kiparo.lessons.kiparoshopping.presentation.navigation.KiparoShoppingPage
import ru.kiparo.lessons.kiparoshopping.presentation.navigation.Register
import ru.kiparo.lessons.kiparoshopping.presentation.navigation.kiparoShoppingTabRowPages
import ru.kiparo.lessons.kiparoshopping.presentation.register.RegisterPage
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.widgets.KiparoShoppingTabRow

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContent {
            var currentPage: KiparoShoppingPage by remember {
                mutableStateOf(Register)
            }

            KiparoShoppingTheme {
                Scaffold(
                    topBar = {
                        KiparoShoppingTabRow(
                            allScreens = kiparoShoppingTabRowPages,
                            onTabSelected = { screen -> currentPage = screen },
                            currentScreen = currentPage
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        currentPage.screen()
                    }
                }
            }
        }
    }
}
