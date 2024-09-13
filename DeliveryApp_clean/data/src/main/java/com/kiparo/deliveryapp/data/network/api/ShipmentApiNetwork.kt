package com.kiparo.deliveryapp.data.network.api

import com.kiparo.deliveryapp.data.network.model.ShipmentNetwork
import com.kiparo.deliveryapp.data.network.model.ShipmentsResponse
import com.squareup.moshi.Moshi
import java.net.HttpURLConnection
import java.net.URL

class ShipmentApiNetwork(
    private val apiUrl: String,
    private val moshi: Moshi,
) : ShipmentApi {
    override fun getShipments(): List<ShipmentNetwork> {
        val url = URL(apiUrl)
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(ShipmentsResponse::class.java)
        val response = jsonAdapter.fromJson(json) as ShipmentsResponse

        return response.shipments
    }
}
