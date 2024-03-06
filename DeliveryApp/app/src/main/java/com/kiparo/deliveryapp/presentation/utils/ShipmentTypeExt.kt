package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.domain.models.ShipmentType

fun ShipmentType.iconResId(): Int =
    when (this) {
        ShipmentType.PARCEL_LOCKER -> {
            // TODO Implement
            // R.drawable.ic_locker
            -1
        }

        ShipmentType.COURIER -> {
            // TODO Implement
            // R.drawable.ic_vehicle
            -1
        }
    }

