package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.core.ui.R
import com.kiparo.deliveryapp.domain.models.ShipmentType

fun ShipmentType.iconResId(): Int =
    when (this) {
        ShipmentType.PARCEL_LOCKER -> {
            R.drawable.ic_locker
        }

        ShipmentType.COURIER -> {
            R.drawable.ic_vehicle
        }
    }

