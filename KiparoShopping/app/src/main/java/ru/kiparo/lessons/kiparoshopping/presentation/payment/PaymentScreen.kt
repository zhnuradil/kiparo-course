package ru.kiparo.lessons.kiparoshopping.presentation.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_24
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_36
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_80
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_width_45
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_20
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_28
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_8
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_12
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_16
import ru.kiparo.lessons.kiparoshopping.ui.tools.CreditCardExpirationDateTransformation
import ru.kiparo.lessons.kiparoshopping.ui.widgets.CreditCard
import ru.kiparo.lessons.kiparoshopping.ui.widgets.DefaultButton
import ru.kiparo.lessons.kiparoshopping.ui.widgets.FormTextField
import ru.kiparo.lessons.kiparoshopping.ui.widgets.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    uiState: PaymentUiState,
    onNameChanged: (String) -> Unit,
    onCardNumberChanged: (String) -> Unit,
    onExpiryDateChanged: (String) -> Unit,
    onCvcChanged: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding_20),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(id = R.string.card_screen_title),
            onBackClick = {},
            action = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add card",
                    modifier = Modifier.clickable(onClick = {}),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(item_height_36))

            CreditCard(
                name = uiState.name,
                cardNumber = uiState.cardNumber,
                expiryDate = uiState.cardExpiryDate
            )

            Spacer(modifier = Modifier.height(item_height_80))

            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.name,
                onChange = onNameChanged,
                label = stringResource(id = R.string.name_field_title),
                placeholder = stringResource(id = R.string.name_field_placeholder)
            )

            Spacer(modifier = Modifier.height(item_height_24))

            FormTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.cardNumber,
                onChange = onCardNumberChanged,
                label = stringResource(id = R.string.card_field_title),
                placeholder = stringResource(id = R.string.card_placeholder)
            )

            Spacer(modifier = Modifier.height(item_height_24))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space_16)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    FormTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.cardExpiryDate,
                        onChange = onExpiryDateChanged,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = CreditCardExpirationDateTransformation(),
                        label = stringResource(id = R.string.date_field_title),
                        placeholder = stringResource(id = R.string.card_expires_placeholder)
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    FormTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.cvc,
                        onChange = onCvcChanged,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = PasswordVisualTransformation(),
                        label = stringResource(id = R.string.cvc_field_title),
                        placeholder = stringResource(id = R.string.card_cvc_placeholder)
                    )

                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = padding_8, top = padding_28),
                        painter = painterResource(id = R.drawable.ic_card),
                        contentDescription = stringResource(id = R.string.card)
                    )
                }
            }

            Spacer(modifier = Modifier.height(item_height_36))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space_12)
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .width(item_width_45)
                        .height(item_width_45)
                        .background(color = MaterialTheme.colorScheme.secondary),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = stringResource(id = R.string.remove_card_content_descr),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                DefaultButton(
                    title = stringResource(id = R.string.use_card_text),
                    onClick = onSubmit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentScreenPreview() {
    KiparoShoppingTheme {
        val uiState = PaymentUiState(
            name = "",
            nameError = false,
            cardNumber = "",
            cardNumberError = false,
            cardExpiryDate = "",
            cardExpiryDateError = false,
            cvc = "",
            cvcError = false
        )

        PaymentScreen(
            uiState = uiState,
            onNameChanged = {},
            onCardNumberChanged = {},
            onExpiryDateChanged = {},
            onCvcChanged = {},
            onSubmit = {},
        )
    }
}