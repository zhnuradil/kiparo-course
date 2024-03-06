package com.kiparo.deliveryapp.data.mappers

import com.kiparo.deliveryapp.data.mock.mockCustomerNetwork
import com.kiparo.deliveryapp.data.mock.mockShipmentNetwork
import com.kiparo.deliveryapp.domain.models.Customer
import com.kiparo.deliveryapp.domain.models.Shipment
import org.junit.Assert
import org.junit.Test

class ShipmentMapperTest {

    @Test
    fun `map ShipmentNetwork to domain is correct`() {
        val shipmentNetwork = mockShipmentNetwork()

        val expected = Shipment(
            number = shipmentNetwork.number,
            type = shipmentNetwork.shipmentType.toDomainShipmentType(),
            status = shipmentNetwork.status.toDomainShipmentStatus(),
            expiryDate = shipmentNetwork.expiryDate,
            storedDate = shipmentNetwork.storedDate,
            pickUpDate = shipmentNetwork.pickUpDate,
            receiver = shipmentNetwork.receiver?.toDomain(),
            sender = shipmentNetwork.sender?.toDomain(),
            highlight = shipmentNetwork.operations.highlight
        )

        val actual = shipmentNetwork.toDomain()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `map CustomerNetwork to domain is correct`() {
        // TODO Implement
        Assert.assertEquals(true, true)
    }
}