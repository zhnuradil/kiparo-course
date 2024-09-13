package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.data.mappers.toDomainShipmentStatus
import com.kiparo.deliveryapp.domain.models.ShipmentStatus
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ShipmentStatusMapperTest(private val input: String, private val expected: com.kiparo.deliveryapp.domain.models.ShipmentStatus) {

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
                arrayOf<Any>("ADOPTED_AT_SORTING_CENTER", com.kiparo.deliveryapp.domain.models.ShipmentStatus.ADOPTED_AT_SORTING_CENTER),
                arrayOf<Any>("SENT_FROM_SORTING_CENTER", com.kiparo.deliveryapp.domain.models.ShipmentStatus.SENT_FROM_SORTING_CENTER),
                arrayOf<Any>("ADOPTED_AT_SOURCE_BRANCH", com.kiparo.deliveryapp.domain.models.ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH),
                arrayOf<Any>("SENT_FROM_SOURCE_BRANCH", com.kiparo.deliveryapp.domain.models.ShipmentStatus.SENT_FROM_SOURCE_BRANCH),
                arrayOf<Any>("AVIZO", com.kiparo.deliveryapp.domain.models.ShipmentStatus.AVIZO),
                arrayOf<Any>("CONFIRMED", com.kiparo.deliveryapp.domain.models.ShipmentStatus.CONFIRMED),
                arrayOf<Any>("CREATED", com.kiparo.deliveryapp.domain.models.ShipmentStatus.CREATED),
                arrayOf<Any>("DELIVERED", com.kiparo.deliveryapp.domain.models.ShipmentStatus.DELIVERED),
                arrayOf<Any>("OTHER", com.kiparo.deliveryapp.domain.models.ShipmentStatus.OTHER),
                arrayOf<Any>("OUT_FOR_DELIVERY", com.kiparo.deliveryapp.domain.models.ShipmentStatus.OUT_FOR_DELIVERY),
                arrayOf<Any>("PICKUP_TIME_EXPIRED", com.kiparo.deliveryapp.domain.models.ShipmentStatus.PICKUP_TIME_EXPIRED),
                arrayOf<Any>("READY_TO_PICKUP", com.kiparo.deliveryapp.domain.models.ShipmentStatus.READY_TO_PICKUP),
                arrayOf<Any>("RETURNED_TO_SENDER", com.kiparo.deliveryapp.domain.models.ShipmentStatus.RETURNED_TO_SENDER),
                arrayOf<Any>("NOT_READY", com.kiparo.deliveryapp.domain.models.ShipmentStatus.NOT_READY)
            )
        }
    }
}