@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kiparo.deliveryapp.data.repository

import com.kiparo.deliveryapp.data.mock.mockOperationsNetwork
import com.kiparo.deliveryapp.data.mock.mockShipmentNetwork
import com.kiparo.deliveryapp.data.network.api.ShipmentApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ShipmentRepositoryImplTest {
    private val api = mockk<com.kiparo.deliveryapp.data.network.api.ShipmentApi>()
    private val dispatcher = UnconfinedTestDispatcher()

    private val shipmentHighlight1 =
        mockShipmentNetwork(operations = mockOperationsNetwork(highlight = true))
    private val shipmentHighlight2 =
        mockShipmentNetwork(operations = mockOperationsNetwork(highlight = true))
    private val shipment1 =
        mockShipmentNetwork(operations = mockOperationsNetwork(highlight = false))
    private val shipment2 =
        mockShipmentNetwork(operations = mockOperationsNetwork(highlight = false))
    private val testShipmentList =
        listOf(shipment1, shipmentHighlight1, shipmentHighlight2, shipment2)

    @Test
    fun `shipments grouped correctly`() = runTest {
        val repository = com.kiparo.deliveryapp.data.repository.ShipmentRepositoryImpl(
            shipmentApi = api,
            dispatcher = dispatcher
        )
        coEvery { api.getShipments() } returns testShipmentList

        val actualShipmentGroup = repository.getGroupedByHighlight()
        val actualReady = actualShipmentGroup.highlights.map { it.number }
        val actualOther = actualShipmentGroup.others.map { it.number }
        val expectedReady = listOf(shipmentHighlight1.number, shipmentHighlight2.number)
        val expectedOther = listOf(shipment1.number, shipment2.number)

        coVerify(exactly = 1) { api.getShipments() }
        Assert.assertEquals(expectedReady, actualReady)
        Assert.assertEquals(expectedOther, actualOther)
    }
}