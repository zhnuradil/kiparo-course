package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentsTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
@Preview
private fun Preview() {
    ShipmentsTopBar()
}