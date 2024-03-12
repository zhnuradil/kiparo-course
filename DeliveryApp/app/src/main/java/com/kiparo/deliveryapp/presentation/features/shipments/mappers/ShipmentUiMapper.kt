package com.kiparo.deliveryapp.presentation.features.shipments.mappers

import com.kiparo.deliveryapp.domain.models.Shipment
import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentGroupUi
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentUi
import com.kiparo.deliveryapp.presentation.utils.getSender
import com.kiparo.deliveryapp.presentation.utils.getStatusDateTime
import com.kiparo.deliveryapp.presentation.utils.getStatusDescription
import com.kiparo.deliveryapp.presentation.utils.iconResId
import com.kiparo.deliveryapp.presentation.utils.titleResId


private const val EMPTY = ""

fun ShipmentGroup.toUi(): ShipmentGroupUi {
    return ShipmentGroupUi(
        highlights = highlights.map { it.toUi() },
        others = others.map { it.toUi() }
    )
}

fun Shipment.toUi(): ShipmentUi {
    return ShipmentUi(
        number = number,
        status = status.titleResId(),
        typeIconResId = type.iconResId(),
        sender = sender?.getSender() ?: EMPTY,
        statusDescription = getStatusDescription(),
        statusDateTime = getStatusDateTime()
    )
}