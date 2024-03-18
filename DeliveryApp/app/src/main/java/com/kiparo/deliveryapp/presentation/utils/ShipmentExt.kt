package com.kiparo.deliveryapp.presentation.utils

import androidx.annotation.StringRes
import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.Shipment
import com.kiparo.deliveryapp.domain.models.ShipmentStatus


private const val EMPTY = ""

@StringRes
fun Shipment.getStatusDescription(): Int {
    return when (status) {
        ShipmentStatus.READY_TO_PICKUP -> {
            expiryDate ?: return R.string.empty
            R.string.waiting_picked_up_to
        }

        ShipmentStatus.DELIVERED -> {
            pickUpDate ?: return R.string.empty
            R.string.status_delivered
        }

        else -> {
            R.string.empty
        }
    }
}

fun Shipment.getStatusDateTime(): String {
    return when (status) {
        ShipmentStatus.READY_TO_PICKUP -> {
            expiryDate ?: return EMPTY
            formatDateTime(expiryDate)
        }

        ShipmentStatus.DELIVERED -> {
            pickUpDate ?: return EMPTY
            formatDateTime(pickUpDate)
        }

        else -> {
            EMPTY
        }
    }
}