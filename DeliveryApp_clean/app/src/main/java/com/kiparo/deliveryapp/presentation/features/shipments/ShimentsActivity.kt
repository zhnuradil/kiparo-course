/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.presentation.features.shipments

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.presentation.core_ui.theme.DeliveryAppTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.kiparo.deliveryapp.BuildConfig
import com.kiparo.deliveryapp.data.network.provideMoshi
import com.kiparo.deliveryapp.data.network.provideShipmentApi
import com.kiparo.deliveryapp.data.repository.ShipmentRepositoryImpl
import com.kiparo.deliveryapp.domain.repository.ShipmentRepository
import com.kiparo.deliveryapp.presentation.features.shipments.mapper.toUi
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentGroupUi
import com.kiparo.deliveryapp.presentation.features.shipments.ui.Shipments
import com.kiparo.deliveryapp.presentation.features.shipments.ui.ShipmentsTopBar
import kotlinx.coroutines.Dispatchers

class ShipmentActivity : ComponentActivity() {

    private val shipmentRepository: ShipmentRepository = ShipmentRepositoryImpl(
        shipmentApi = provideShipmentApi(apiUrl = BuildConfig.API_URL, moshi = provideMoshi()),
        dispatcher = Dispatchers.IO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ShipmentsTopBar()
                        ShipmentsScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun ShipmentsScreen() {
        var shipmentsState by remember { mutableStateOf(ShipmentGroupUi(emptyList(), emptyList())) }
        var loading by remember { mutableStateOf(true) }
        Log.d("ShipmentsScreen", "ShipmentsScreen")

        LaunchedEffect(Unit) {
            Log.d("ShipmentsScreen", "Launched Effect")
            val shipments = shipmentRepository.getGroupedByHighlight()
            shipmentsState =
                shipments.toUi()
            loading = false
        }

        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Shipments(group = shipmentsState)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

//    val mockShipmentGroup = ShipmentGroupUi(
//        highlights = listOf(),
//        others = listOf()
//    )
//
//    DeliveryAppTheme {
//        Column {
//            ShipmentsTopBar()
//            Shipments(
//                group = mockShipmentGroup
//            )
//        }
//    }
}
