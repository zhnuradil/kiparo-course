package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_1

private const val WEIGHT = 1.0f

@Composable
fun ShipmentGroupTitle(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Divider(
            thickness = space_1,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .weight(WEIGHT)
                .padding(start = padding_16, end = padding_16)
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.surfaceVariant,
            style = MaterialTheme.typography.titleSmall
        )
        Divider(
            thickness = space_1,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .weight(WEIGHT)
                .padding(start = padding_16, end = padding_16)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    ShipmentGroupTitle(title = "Ready to pickup")
}