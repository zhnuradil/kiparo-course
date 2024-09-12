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
            return listOf(
                arrayOf<Any>("ADOPTED_AT_SORTING_CENTER", ShipmentStatus.ADOPTED_AT_SORTING_CENTER),
                arrayOf<Any>("SENT_FROM_SORTING_CENTER", ShipmentStatus.SENT_FROM_SORTING_CENTER),
                arrayOf<Any>("ADOPTED_AT_SOURCE_BRANCH", ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH),
                arrayOf<Any>("SENT_FROM_SOURCE_BRANCH", ShipmentStatus.SENT_FROM_SOURCE_BRANCH),
                arrayOf<Any>("AVIZO", ShipmentStatus.AVIZO),
                arrayOf<Any>("CONFIRMED", ShipmentStatus.CONFIRMED),
                arrayOf<Any>("CREATED", ShipmentStatus.CREATED),
                arrayOf<Any>("DELIVERED", ShipmentStatus.DELIVERED),
                arrayOf<Any>("OTHER", ShipmentStatus.OTHER),
                arrayOf<Any>("OUT_FOR_DELIVERY", ShipmentStatus.OUT_FOR_DELIVERY),
                arrayOf<Any>("PICKUP_TIME_EXPIRED", ShipmentStatus.PICKUP_TIME_EXPIRED),
                arrayOf<Any>("READY_TO_PICKUP", ShipmentStatus.READY_TO_PICKUP),
                arrayOf<Any>("RETURNED_TO_SENDER", ShipmentStatus.RETURNED_TO_SENDER),
                arrayOf<Any>("NOT_READY", ShipmentStatus.NOT_READY)
            )
        }
    }
}