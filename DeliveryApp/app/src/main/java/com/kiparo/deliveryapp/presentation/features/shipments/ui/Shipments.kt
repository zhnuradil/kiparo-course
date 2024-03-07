package com.kiparo.deliveryapp.presentation.features.shipments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding_16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space_16

// For Students, read this:
// https://developer.android.com/jetpack/compose/lists

@Composable
fun Shipments(group: ShipmentGroup, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(space_16),
        contentPadding = PaddingValues(padding_16)
    ) {
        item {
            Header(title = stringResource(id = R.string.ready_to_pickup))
        }
        group.highlights.forEach {

        }
        item {
            Header(title = stringResource(id = R.string.others))
        }
        group.others.forEach {

        }
    }
}