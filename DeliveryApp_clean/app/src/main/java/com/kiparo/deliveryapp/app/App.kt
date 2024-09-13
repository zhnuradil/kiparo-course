package com.kiparo.deliveryapp.app

import android.app.Application
import com.kiparo.deliveryapp.BuildConfig
import com.kiparo.deliveryapp.data.network.api.DateTimeAdapter
import com.kiparo.deliveryapp.data.network.api.ShipmentApi
import com.kiparo.deliveryapp.data.network.api.ShipmentApiNetwork
import com.kiparo.deliveryapp.data.repository.ShipmentRepositoryImpl
import com.kiparo.deliveryapp.core.di.DiProvider
import com.kiparo.deliveryapp.di.GlobalDiImpl
import com.kiparo.deliveryapp.domain.repository.ShipmentRepository
import com.kiparo.deliveryapp.domain.use_case.GetGroupedByHighlightUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDi()
    }

    private fun setupDi() {
        DiProvider.di = GlobalDiImpl()
        DiProvider.di.add(
            key = Moshi::class,
            object_ = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(DateTimeAdapter())
                .build()
        )
        DiProvider.di.add(
            key = String::class,
            object_ = BuildConfig.API_URL
        )
        DiProvider.di.add(
            key = CoroutineDispatcher::class,
            object_ = Dispatchers.IO
        )
        DiProvider.di.add(
            key = ShipmentApi::class,
            object_ = ShipmentApiNetwork(
                apiUrl = DiProvider.di.get(String::class),
                moshi = DiProvider.di.get(Moshi::class)
            )
        )
        DiProvider.di.add(
            key = ShipmentRepository::class,
            object_ = ShipmentRepositoryImpl(
                shipmentApi = DiProvider.di.get(ShipmentApi::class),
                dispatcher = DiProvider.di.get(CoroutineDispatcher::class)
            )
        )
        DiProvider.di.add(
            key = GetGroupedByHighlightUseCase::class,
            object_ = GetGroupedByHighlightUseCase(
                shipmentRepository = DiProvider.di.get(ShipmentRepository::class),
            )
        )
    }
}