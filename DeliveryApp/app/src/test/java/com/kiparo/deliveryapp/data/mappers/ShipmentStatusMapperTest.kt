package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.domain.models.ShipmentStatus
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ShipmentStatusMapperTest(private val input: String, private val expected: ShipmentStatus) {

    @Test
    fun `map Shipment Status to domain is correct`() {
        val actual = input.toDomainShipmentStatus()
        Assert.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<*> {
            return listOf<Any>(
                arrayOf("ADOPTED_AT_SORTING_CENTER", ShipmentStatus.ADOPTED_AT_SORTING_CENTER),
                arrayOf("SENT_FROM_SORTING_CENTER", ShipmentStatus.SENT_FROM_SORTING_CENTER),
                arrayOf("ADOPTED_AT_SOURCE_BRANCH", ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH),
                arrayOf("SENT_FROM_SOURCE_BRANCH", ShipmentStatus.SENT_FROM_SOURCE_BRANCH),
                arrayOf("AVIZO", ShipmentStatus.AVIZO),
                arrayOf("CONFIRMED", ShipmentStatus.CONFIRMED),
                arrayOf("CREATED", ShipmentStatus.CREATED),
                arrayOf("DELIVERED", ShipmentStatus.DELIVERED),
                arrayOf("OTHER", ShipmentStatus.OTHER),
                arrayOf("OUT_FOR_DELIVERY", ShipmentStatus.OUT_FOR_DELIVERY),
                arrayOf("PICKUP_TIME_EXPIRED", ShipmentStatus.PICKUP_TIME_EXPIRED),
                arrayOf("READY_TO_PICKUP", ShipmentStatus.READY_TO_PICKUP),
                arrayOf("RETURNED_TO_SENDER", ShipmentStatus.RETURNED_TO_SENDER),
                arrayOf("NOT_READY", ShipmentStatus.NOT_READY),
            )
        }
    }
}