package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.presentation.core_ui.theme.bodyMediumBold
import com.kiparo.deliveryapp.presentation.core_ui.theme.elevation10
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space20
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentUi

@Composable
fun ShipmentItem(item: ShipmentUi, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .shadow(elevation = elevation10, spotColor = Color(0xFFE6E7E8))
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = padding16)
                .padding(top = padding16)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ShipmentLabel(
                        title = stringResource(id = R.string.id).uppercase(),
                        text = item.number
                    )
                    Icon(
                        painter = painterResource(id = item.typeIconResId),
                        contentDescription = "shipment type",
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.height(space16))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ShipmentLabel(
                        title = stringResource(id = R.string.status).uppercase(),
                        text = stringResource(id =item.status),
                        textStyle = MaterialTheme.typography.bodyMediumBold
                    )
                    Spacer(modifier = Modifier.width(space16))
                    ShipmentLabel(
                        title = stringResource(id = item.statusDescription).uppercase(),
                        text = item.statusDateTime,
                        horizontalAlignment = Alignment.End
                    )
                }
                Spacer(modifier = Modifier.height(space16))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    ShipmentLabel(
                        modifier = Modifier.padding(bottom = padding16),
                        title = stringResource(id = R.string.sender).uppercase(),
                        text = item.sender,
                        textStyle = MaterialTheme.typography.bodyMediumBold
                    )
                    Spacer(modifier = Modifier.width(space20))
                    ShipmentMoreButton()
                }
            }
        }
    }
}

