/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.domain.models.ShipmentType

internal fun String.toDomainShipmentType(): ShipmentType {
    return enumValues<ShipmentType>().firstOrNull { it.name == this }
        ?: throw IllegalArgumentException("Invalid shipment type: $this")
}
