/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.domain.usecases.cart

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.repository.CartRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithoutParams
import kotlinx.coroutines.flow.Flow

class ObserveCartUseCase(
    private val repository: CartRepository,
) : UseCaseWithoutParams<Flow<List<MenuItem>>> {
    override suspend fun execute(): Flow<List<MenuItem>> =
        repository.observe()
}