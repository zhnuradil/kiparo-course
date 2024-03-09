package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.Shipment
import com.kiparo.deliveryapp.domain.models.ShipmentStatus

fun Shipment.senderAsUIText(): String {
    if (sender == null) return "Unknown"
    return sender.name.ifBlank { sender.email }
}

fun Shipment.isDateVisible(): Boolean {
    return status == ShipmentStatus.READY_TO_PICKUP || status == ShipmentStatus.DELIVERED
}

fun Shipment.dateTitleResId(): Int {
    return when (status) {
        ShipmentStatus.READY_TO_PICKUP -> R.string.waiting_picked_up_to
        ShipmentStatus.DELIVERED -> R.string.status_delivered
        else -> -1
    }
}

fun Shipment.formattedDateTime(): String {
    return when (status) {
        ShipmentStatus.READY_TO_PICKUP -> {
            if (storedDate == null) "unknown date" else formatDateTime(storedDate)
        }

        ShipmentStatus.DELIVERED -> {
            if (expiryDate == null) "unknown date" else formatDateTime(expiryDate)
        }

        else -> "unknown date"
    }
}