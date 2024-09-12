/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.presentation.features.shipments.mapper

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.Customer
import com.kiparo.deliveryapp.domain.models.Shipment
import com.kiparo.deliveryapp.domain.models.ShipmentGroup
import com.kiparo.deliveryapp.domain.models.ShipmentStatus
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentGroupUi
import com.kiparo.deliveryapp.presentation.features.shipments.models.ShipmentUi
import com.kiparo.deliveryapp.presentation.utils.formatDateTime
import com.kiparo.deliveryapp.presentation.utils.iconResId
import com.kiparo.deliveryapp.presentation.utils.titleResId

private const val EMPTY = ""

fun ShipmentGroup.toUi(): ShipmentGroupUi {
    return ShipmentGroupUi(
        highlights = highlights.map { it.toUi() },
        others = others.map { it.toUi() }
    )
}

private fun Shipment.toUi(): ShipmentUi {
    return ShipmentUi(
        number = number,
        status = status.titleResId(),
        typeIconResId = type.iconResId(),
        sender = sender?.getSender() ?: EMPTY,
        statusDescription = getStatusDescription(shipment = this),
        statusDateTime = getStatusDateTime(shipment = this)
    )
}

@StringRes
private fun getStatusDescription(shipment: Shipment): Int {
    return when (shipment.status) {
        ShipmentStatus.READY_TO_PICKUP -> {
            shipment.expiryDate ?: return R.string.empty
            R.string.waiting_picked_up_to
        }

        ShipmentStatus.DELIVERED -> {
            shipment.pickUpDate ?: return R.string.empty
            R.string.status_delivered
        }

        else -> {
            R.string.empty
        }
    }
}

private fun getStatusDateTime(shipment: Shipment): String {
    return when (shipment.status) {
        ShipmentStatus.READY_TO_PICKUP -> {
            shipment.expiryDate ?: return EMPTY
            formatDateTime(shipment.expiryDate)
        }

        ShipmentStatus.DELIVERED -> {
            shipment.pickUpDate ?: return EMPTY
            formatDateTime(shipment.pickUpDate)
        }

        else -> {
            EMPTY
        }
    }
}

private fun Customer.getSender(): String {
    if (name.isNotEmpty()) {
        return name
    }
    return email
}