@file:OptIn(ExperimentalMaterial3Api::class)

package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.presentation.core_ui.theme.bodyMediumBold

@Composable
fun ShipmentsTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMediumBold
            )
        })
}

@Composable
@Preview
private fun Preview() {
    ShipmentsTopBar()
}