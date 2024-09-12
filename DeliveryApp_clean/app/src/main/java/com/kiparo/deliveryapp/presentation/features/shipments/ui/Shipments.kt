package com.kiparo.deliveryapp.presentation.features.shipments.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.presentation.core_ui.theme.padding16
import com.kiparo.deliveryapp.presentation.core_ui.theme.space16
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentGroupUi

// For Students, read this:
// https://developer.android.com/jetpack/compose/lists

@Composable
fun Shipments(group: ShipmentGroupUi, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space16),
        contentPadding = PaddingValues(top = padding16, bottom = padding16),
        modifier = modifier.fillMaxSize()
    ) {
        item(key = "ready_for_pickups") {
            ShipmentGroupTitle(title = stringResource(id = R.string.ready_to_pickup))
        }
        items(group.highlights, key = {
            it.number
        }) {
            ShipmentItem(item = it)
        }
        item(key = "others") {
            ShipmentGroupTitle(title = stringResource(id = R.string.others))
        }
        items(group.others, key = {
            it.number
        }) {
            ShipmentItem(item = it)
        }
    }
}