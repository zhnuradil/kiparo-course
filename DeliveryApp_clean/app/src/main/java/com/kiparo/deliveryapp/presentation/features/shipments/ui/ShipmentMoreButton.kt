package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding0
import com.kiparo.deliveryapp.presentation.core_ui.theme.space5

@Composable
fun ShipmentMoreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier.padding(padding0),
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(id = R.string.more),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(space5))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "arrow",
            tint = Color.Unspecified
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    ShipmentMoreButton()
}