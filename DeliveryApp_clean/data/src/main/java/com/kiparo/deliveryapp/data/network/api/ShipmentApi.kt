package com.kiparo.deliveryapp.data.network.api

import com.kiparo.deliveryapp.data.network.model.ShipmentNetwork

interface ShipmentApi {
    fun getShipments(): List<ShipmentNetwork>
}
