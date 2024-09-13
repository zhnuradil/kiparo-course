/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.data.network.model.CustomerNetwork
import com.kiparo.deliveryapp.data.network.model.ShipmentNetwork
import com.kiparo.deliveryapp.domain.models.Customer
import com.kiparo.deliveryapp.domain.models.Shipment

internal fun ShipmentNetwork.toDomain(): Shipment {
    return Shipment(
        number = number,
        type = shipmentType.toDomainShipmentType(),
        status = status.toDomainShipmentStatus(),
        expiryDate = expiryDate,
        storedDate = storedDate,
        pickUpDate = pickUpDate,
        receiver = receiver?.toDomain(),
        sender = sender?.toDomain(),
        highlight = operations.highlight
    )
}

internal fun CustomerNetwork.toDomain(): Customer {
    return Customer(
        email = email ?: "",
        phoneNumber = phoneNumber ?: "",
        name = name ?: ""
    )
}