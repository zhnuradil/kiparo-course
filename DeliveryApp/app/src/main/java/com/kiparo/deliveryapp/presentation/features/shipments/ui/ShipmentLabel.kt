package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_4

@Composable
fun ShipmentLabel(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.height(space_4))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = textStyle
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    ShipmentLabel(title = "number", text = "5462523642352346345")
}