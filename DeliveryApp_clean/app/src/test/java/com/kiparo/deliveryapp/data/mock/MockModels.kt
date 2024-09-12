/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.deliveryapp.data.mock

import com.kiparo.deliveryapp.data.network.model.CustomerNetwork
import com.kiparo.deliveryapp.data.network.model.EventLogNetwork
import com.kiparo.deliveryapp.data.network.model.OperationsNetwork
import com.kiparo.deliveryapp.data.network.model.ShipmentNetwork
import java.time.ZonedDateTime
import kotlin.random.Random

internal fun mockShipmentNetwork(
    number: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    type: String = "PARCEL_LOCKER",
    status: String = "DELIVERED",
    sender: CustomerNetwork? = mockCustomerNetwork(),
    receiver: CustomerNetwork? = mockCustomerNetwork(),
    operations: OperationsNetwork = mockOperationsNetwork(),
    eventLog: List<EventLogNetwork> = emptyList(),
    openCode: String? = null,
    expireDate: ZonedDateTime? = null,
    storedDate: ZonedDateTime? = null,
    pickupDate: ZonedDateTime? = null
) = ShipmentNetwork(
    number = number,
    shipmentType = type,
    status = status,
    eventLog = eventLog,
    openCode = openCode,
    expiryDate = expireDate,
    storedDate = storedDate,
    pickUpDate = pickupDate,
    receiver = receiver,
    sender = sender,
    operations = operations
)

internal fun mockCustomerNetwork(
    email: String = "name@email.com",
    phoneNumber: String = "123 123 123",
    name: String = "Ivan Kowalski"
) = CustomerNetwork(
    email = email,
    phoneNumber = phoneNumber,
    name = name
)

internal fun mockOperationsNetwork(
    manualArchive: Boolean = false,
    delete: Boolean = false,
    collect: Boolean = false,
    highlight: Boolean = false,
    expandAvizo: Boolean = false,
    endOfWeekCollection: Boolean = false
) = OperationsNetwork(
    manualArchive = manualArchive,
    delete = delete,
    collect = collect,
    highlight = highlight,
    expandAvizo = expandAvizo,
    endOfWeekCollection = endOfWeekCollection
)