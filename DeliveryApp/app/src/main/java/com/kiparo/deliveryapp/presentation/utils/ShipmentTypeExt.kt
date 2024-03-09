package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.ShipmentType

fun ShipmentType.iconResId(): Int =
    when (this) {
        ShipmentType.PARCEL_LOCKER -> {
             R.drawable.ic_building
        }

        ShipmentType.COURIER -> {
             R.drawable.ic_auto
        }
    }

