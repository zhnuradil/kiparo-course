package com.kiparo.pizzaapp.data.repository

import com.kiparo.pizzaapp.data.mappers.toDomain
import com.kiparo.pizzaapp.data.mappers.toStorage
import com.kiparo.pizzaapp.data.storage.api.CartStorage
import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val cartStorage: CartStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CartRepository {

    override suspend fun add(item: MenuItem) =
        withContext(dispatcher) {
            cartStorage.add(item.toStorage())
        }

    override suspend fun remove(id: String) =
        cartStorage.remove(id)

    override suspend fun observe(): Flow<List<MenuItem>> =
        cartStorage.observe().flowOn(
            dispatcher
        ).map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun observeSize(): Flow<Int> =
        cartStorage.observeSize().flowOn(
            dispatcher
        )
}

