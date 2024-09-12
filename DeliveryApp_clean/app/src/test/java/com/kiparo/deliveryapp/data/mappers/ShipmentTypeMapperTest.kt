package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.domain.models.ShipmentType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ShipmentTypeMapperTest(private val input: String, private val expected: ShipmentType) {

    @Test
    fun `map shipment type to domain is correct`() {
        val actual = input.toDomainShipmentType()
        Assert.assertEquals(expected, actual)
    }

//    @Test
//    fun `map Shipment Status to domain is incorrect, threw the exception IllegalArgumentException`() {
//        Assert.assertThrows(IllegalArgumentException::class.java) {
//            "NO_STATUS".toDomainShipmentStatus()
//        }
//    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<*> {
            return listOf(
                arrayOf<Any>("PARCEL_LOCKER", ShipmentType.PARCEL_LOCKER),
                arrayOf<Any>("COURIER", ShipmentType.COURIER)
            )
        }
    }
}