package com.kiparo.pizzaapp.data.repository

import com.kiparo.pizzaapp.data.mappers.toDomain
import com.kiparo.pizzaapp.data.storage.api.PromotionStorage
import com.kiparo.pizzaapp.domain.models.Promotion
import com.kiparo.pizzaapp.domain.repository.PromotionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PromotionRepositoryImpl(
    private val promoStorage: PromotionStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PromotionRepository {
    override suspend fun get(): Promotion =
        withContext(dispatcher) {
            promoStorage.get().toDomain()
        }

}