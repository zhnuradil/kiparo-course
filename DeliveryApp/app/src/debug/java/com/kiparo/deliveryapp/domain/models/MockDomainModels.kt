package com.kiparo.deliveryapp.domain.models

import java.time.ZonedDateTime
import kotlin.random.Random

internal fun mockShipment(
    number: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    type: ShipmentType = ShipmentType.PARCEL_LOCKER,
    status: ShipmentStatus = ShipmentStatus.DELIVERED,
    expireDate: ZonedDateTime? = null,
    storedDate: ZonedDateTime? = null,
    pickupDate: ZonedDateTime? = null,
    sender: Customer? = mockCustomer(),
    receiver: Customer? = mockCustomer(),
    highlight: Boolean = false
) = Shipment(
    number = number,
    type = type,
    status = status,
    expiryDate = expireDate,
    storedDate = storedDate,
    pickUpDate = pickupDate,
    receiver = receiver,
    sender = sender,
    highlight = highlight
)

internal fun mockCustomer(
    email: String = "info@kiparo.ru",
    phoneNumber: String = "123 123 123",
    name: String = "Kiparo Academy"
) = Customer(
    email = email,
    phoneNumber = phoneNumber,
    name = name
)

internal fun mockShipments(): List<Shipment> {
    return listOf(
        mockShipment(highlight = true),
        mockShipment(highlight = true),
        mockShipment(highlight = false),
        mockShipment(highlight = false),
        mockShipment(highlight = false)
    )
}

internal fun mockShipmentsGroup(): ShipmentGroup {
    val highlights = listOf(
        mockShipment(highlight = true),
        mockShipment(highlight = true)
    )

    val others = listOf(
        mockShipment(),
        mockShipment()
    )

    return ShipmentGroup(highlights = highlights, others = others)
}