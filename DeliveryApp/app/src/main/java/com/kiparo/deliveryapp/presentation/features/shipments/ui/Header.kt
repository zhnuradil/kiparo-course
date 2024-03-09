package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_20
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_1
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_20

@Composable
fun Header(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(start = padding_20, end = padding_20),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(space_1)
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        Text(
            modifier = Modifier.padding(start = space_16, end = space_16),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.surfaceVariant,
            style = MaterialTheme.typography.titleSmall
        )
        Box(
            modifier = Modifier
                .height(space_1)
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(end = space_20)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Header(title = "Ready to pickup")
}