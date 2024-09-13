package com.kiparo.deliveryapp.domain.models

data class ShipmentGroup(
    val highlights: List<Shipment>,
    val others: List<Shipment>
)
