package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.Shipment
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_16
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_20
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_8
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_4
import com.kiparo.deliveryapp.presentation.utils.dateTitleResId
import com.kiparo.deliveryapp.presentation.utils.formattedDateTime
import com.kiparo.deliveryapp.presentation.utils.senderAsUIText
import com.kiparo.deliveryapp.presentation.utils.iconResId
import com.kiparo.deliveryapp.presentation.utils.isDateVisible
import com.kiparo.deliveryapp.presentation.utils.titleResId

@Composable
fun ShipmentItem(shipment: Shipment, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(start = padding_20, end = padding_20, top = padding_16, bottom = padding_8)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.id).uppercase(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = shipment.number,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.width(space_16))
            Icon(
                painter = painterResource(id = shipment.type.iconResId()),
                contentDescription = "Auto icon",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(space_16))
        Row {
            Column {
                Text(
                    text = stringResource(id = R.string.status).uppercase(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = stringResource(id = shipment.status.titleResId()),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if (shipment.isDateVisible()) {
                Spacer(modifier = Modifier.width(space_16))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringResource(id = shipment.dateTitleResId()).uppercase(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = shipment.formattedDateTime(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(space_16))
        Row(verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.status).uppercase(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = shipment.senderAsUIText(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(space_16))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.more).lowercase(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(space_4))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Arrow right icon",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
//    ShipmentItem()
}