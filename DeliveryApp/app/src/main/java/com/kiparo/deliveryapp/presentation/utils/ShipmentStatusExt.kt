package com.kiparo.deliveryapp.presentation.utils

import com.kiparo.deliveryapp.R
import com.kiparo.deliveryapp.domain.models.ShipmentStatus

fun ShipmentStatus.titleResId(): Int =
    when (this) {
        ShipmentStatus.ADOPTED_AT_SORTING_CENTER -> {
            R.string.status_adopted_at_sorting_center
        }

        ShipmentStatus.SENT_FROM_SORTING_CENTER -> {
            R.string.status_sent_from_sorting_center
        }

        ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH -> {
            R.string.status_adopted_at_source_branch
        }

        ShipmentStatus.SENT_FROM_SOURCE_BRANCH -> {
            R.string.status_sent_from_source_branch
        }

        ShipmentStatus.AVIZO -> {
            R.string.status_avizo
        }

        ShipmentStatus.CONFIRMED -> {
            R.string.status_confirmed
        }

        ShipmentStatus.CREATED -> {
            R.string.status_created
        }

        ShipmentStatus.DELIVERED -> {
            R.string.status_delivered
        }

        ShipmentStatus.OTHER -> {
            R.string.status_other
        }

        ShipmentStatus.OUT_FOR_DELIVERY -> {
            R.string.status_out_for_delivery
        }

        ShipmentStatus.PICKUP_TIME_EXPIRED -> {
            R.string.status_adopted_at_sorting_center
        }

        ShipmentStatus.READY_TO_PICKUP -> {
            R.string.status_ready_to_pickup
        }

        ShipmentStatus.RETURNED_TO_SENDER -> {
            R.string.status_returned_to_sender
        }

        ShipmentStatus.NOT_READY -> {
            R.string.status_not_ready
        }
    }
