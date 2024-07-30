package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.border_radius_20
import ru.kiparo.lessons.kiparoshopping.ui.theme.cardGradient
import ru.kiparo.lessons.kiparoshopping.ui.theme.card_height
import ru.kiparo.lessons.kiparoshopping.ui.theme.card_icon_height
import ru.kiparo.lessons.kiparoshopping.ui.theme.card_icon_width
import ru.kiparo.lessons.kiparoshopping.ui.theme.card_width
import ru.kiparo.lessons.kiparoshopping.ui.theme.creditCardTextStyle
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_22
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_3
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_8
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_12
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_28
import ru.kiparo.lessons.kiparoshopping.ui.tools.CreditCardExpirationDateTransformation
import ru.kiparo.lessons.kiparoshopping.ui.tools.getCardResource
import ru.kiparo.lessons.kiparoshopping.ui.tools.getCardTypeFromNumber

@Composable
fun CreditCard(
    name: String,
    cardNumber: String,
    expiryDate: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredWidth(card_width)
            .requiredHeight(card_height)
            .clip(shape = RoundedCornerShape(size = border_radius_20))
            .background(brush = cardGradient)
            .padding(all = padding_22)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                CardSystemIcon(
                    number = cardNumber,
                    modifier = Modifier.padding(
                        top = padding_3,
                        end = padding_3
                    )
                )
            }

            Spacer(modifier = Modifier.height(space_28))
            CardNumber(
                value = cardNumber,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(space_12))
            Expiration(expiryDate = expiryDate)

            Spacer(modifier = Modifier.height(space_12))
            OwnerName(
                name = name,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Expiration(expiryDate: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.Bottom) {
        Column(modifier = Modifier.padding(end = padding_8)) {
            Text(
                text = stringResource(id = R.string.valid),
                style = MaterialTheme.typography.creditCardTextStyle.copy(fontSize = 6.sp)
            )
            Text(
                text = stringResource(id = R.string.trhu),
                style = MaterialTheme.typography.creditCardTextStyle.copy(fontSize = 6.sp)
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.month_year),
                style = MaterialTheme.typography.creditCardTextStyle.copy(fontSize = 6.sp)
            )
            BasicTextField(
                value = expiryDate,
                onValueChange = { },
                readOnly = true,
                singleLine = true,
                visualTransformation = CreditCardExpirationDateTransformation(),
                textStyle = MaterialTheme.typography.creditCardTextStyle.copy(fontSize = 14.sp)
            )
        }
    }
}

@Composable
internal fun CardSystemIcon(number: String, modifier: Modifier = Modifier) {
    val resId = getCardResource(getCardTypeFromNumber(number))
    return CardIcon(iconRes = resId, modifier = modifier)
}

@Composable
internal fun CardIcon(iconRes: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = stringResource(id = R.string.card_type_content),
        modifier = modifier
            .requiredWidth(card_icon_width)
            .requiredHeight(card_icon_height)
    )
}

@Composable
internal fun OwnerName(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = name,
        style = MaterialTheme.typography.creditCardTextStyle.copy(fontSize = 12.sp)
    )
}

@Preview
@Composable
fun CreditCardPreview() {
    CreditCard(name = "Ivan Kiparo", cardNumber = "9999999999999999", "1223")
}