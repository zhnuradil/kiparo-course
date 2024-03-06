/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.presentation.features.shipments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.deliveryapp.BuildConfig
import com.kiparo.deliveryapp.data.network.provideMoshi
import com.kiparo.deliveryapp.data.network.provideShipmentApi
import com.kiparo.deliveryapp.data.repository.ShipmentRepositoryImpl
import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.domain.models.mockShipmentsGroup
import com.kiparo.deliveryapp.domain.repository.ShipmentRepository
import com.kiparo.deliveryapp.presentation.core_ui.theme.DeliveryAppTheme
import com.kiparo.deliveryapp.presentation.features.shipments.ui.Shipments
import com.kiparo.deliveryapp.presentation.features.shipments.ui.ShipmentsTopBar
import kotlinx.coroutines.Dispatchers

class ShipmentsActivity : ComponentActivity() {

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
        val shipments = remember { mutableStateOf(ShipmentGroup(emptyList(), emptyList())) }
        val loading = remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            shipments.value = shipmentRepository.getGroupedByHighlight()
            loading.value = false
        }

        if (loading.value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Shipments(group = shipments.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    DeliveryAppTheme {
        Column {
            ShipmentsTopBar()
            Shipments(group = mockShipmentsGroup())
        }
    }
}
