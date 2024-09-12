package com.kiparo.deliveryapp.data.network

import com.kiparo.deliveryapp.data.network.api.DateTimeAdapter
import com.kiparo.deliveryapp.data.network.api.ShipmentApi
import com.kiparo.deliveryapp.data.network.api.ShipmentApiNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

internal fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateTimeAdapter())
        .build()
}

internal fun provideShipmentApi(apiUrl: String, moshi: Moshi): ShipmentApi {
    return ShipmentApiNetwork(
        apiUrl = apiUrl,
        moshi = moshi
    )
}