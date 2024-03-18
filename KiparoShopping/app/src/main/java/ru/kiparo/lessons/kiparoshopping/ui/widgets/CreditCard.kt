package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreditCard(
    name: String,
    cardNumber: String,
    expiryDate: String,
    modifier: Modifier = Modifier
) {
    //TODO: Implement
}

@Composable
private fun Expiration(expiryDate: String, modifier: Modifier) {
    //TODO: Implement
}

@Composable
internal fun CardSystemIcon(number: String, modifier: Modifier = Modifier) {

    //TODO: Implement dynamic card icon based on number. Use getCardTypeFromNumber() from tools

}

@Composable
internal fun CardNumber(value: String, modifier: Modifier = Modifier) {
    //TODO: Implement
}

@Composable
internal fun OwnerName(name: String, modifier: Modifier = Modifier) {
    //TODO: Implement
}

@Preview
@Composable
fun CreditCardPreview() {
    CreditCard(name = "Ivan Kiparo", cardNumber = "9999999999999999", "1223")
}