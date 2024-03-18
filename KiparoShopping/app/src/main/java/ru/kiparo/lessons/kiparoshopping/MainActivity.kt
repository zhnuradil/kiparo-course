package ru.kiparo.lessons.kiparoshopping

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kiparo.lessons.kiparoshopping.register.RegisterScreen
import ru.kiparo.lessons.kiparoshopping.register.RegisterViewModel
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContent {
            KiparoShoppingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val viewModel: RegisterViewModel = viewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    RegisterScreen(
                        uiState = uiState,
                        onNameChange = viewModel::onNameChange,
                        onEmailChange = viewModel::onEmailChange,
                        onMobileChange = viewModel::onMobileChange,
                        onPasswordChange = viewModel::onPasswordChange,
                        onPasswordConfirmChange = viewModel::onPasswordConfirmChange,
                        onReferalChange = viewModel::onReferalChange,
                        onSubmit = viewModel::onSubmit,
                    )
                }
            }
        }
    }
}
