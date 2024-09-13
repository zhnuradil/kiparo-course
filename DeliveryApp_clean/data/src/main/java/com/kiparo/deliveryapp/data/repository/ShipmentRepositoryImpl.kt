/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.data.repository

import com.kiparo.deliveryapp.data.mappers.toDomain
import com.kiparo.deliveryapp.data.network.api.ShipmentApi
import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.domain.repository.ShipmentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShipmentRepositoryImpl(
    private val shipmentApi: ShipmentApi,
    private val dispatcher: CoroutineDispatcher
) : ShipmentRepository {
    override suspend fun getGroupedByHighlight(): ShipmentGroup = withContext(dispatcher) {
        val group = shipmentApi
            .getShipments()
            .groupBy { it.operations.highlight }
            .mapValues { it.value.map { shipment -> shipment.toDomain() } }

        ShipmentGroup(
            highlights = group[true] ?: emptyList(),
            others = group[false] ?: emptyList()
        )
    }
}


