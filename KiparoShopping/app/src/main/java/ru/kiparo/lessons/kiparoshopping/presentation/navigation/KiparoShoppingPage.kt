package ru.kiparo.lessons.kiparoshopping.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import ru.kiparo.lessons.kiparoshopping.presentation.payment.PaymentPage
import ru.kiparo.lessons.kiparoshopping.presentation.register.RegisterPage
import ru.kiparo.lessons.kiparoshopping.presentation.verification.VerificationPage

interface KiparoShoppingPage {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object Register : KiparoShoppingPage {
    override val icon = Icons.Outlined.Person
    override val route = "register"
    override val screen: @Composable () -> Unit = { RegisterPage() }
}

object Verification : KiparoShoppingPage {
    override val icon = Icons.Outlined.Call
    override val route = "verification"
    override val screen: @Composable () -> Unit = { VerificationPage() }
}

object Payment : KiparoShoppingPage {
    override val icon = Icons.Outlined.Add
    override val route = "payment"
    override val screen: @Composable () -> Unit = { PaymentPage() }
}

val kiparoShoppingTabRowPages = listOf(
    Register,
    Verification,
    Payment
)