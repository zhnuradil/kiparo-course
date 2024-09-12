/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.domain.models.ShipmentStatus

internal fun String.toDomainShipmentStatus(): ShipmentStatus {
    return enumValues<ShipmentStatus>().firstOrNull { it.name == this }
        ?: throw IllegalArgumentException("Invalid shipment status: $this")
}