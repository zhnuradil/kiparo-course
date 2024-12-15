package com.kiparo.pizzaapp.domain.repository

import com.kiparo.pizzaapp.domain.models.MenuItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun add(item: MenuItem)
    suspend fun observe(): Flow<List<MenuItem>>
    suspend fun observeSize(): Flow<Int>
    suspend fun remove(id: String)
}