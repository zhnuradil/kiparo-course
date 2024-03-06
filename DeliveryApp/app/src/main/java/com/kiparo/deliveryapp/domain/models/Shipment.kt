package com.kiparo.deliveryapp.domain.models

import java.time.ZonedDateTime

data class Shipment(
    val number: String,
    val type: ShipmentType,
    val status: ShipmentStatus,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiver: Customer?,
    val sender: Customer?,
    val highlight: Boolean
)
