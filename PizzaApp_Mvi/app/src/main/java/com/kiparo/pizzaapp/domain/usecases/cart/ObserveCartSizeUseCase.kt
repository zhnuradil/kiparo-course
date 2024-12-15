/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.domain.usecases.cart

import com.kiparo.pizzaapp.domain.repository.CartRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithoutParams
import kotlinx.coroutines.flow.Flow

class ObserveCartSizeUseCase(
    private val repository: CartRepository,
) : UseCaseWithoutParams<Flow<Int>> {
    override suspend fun execute(): Flow<Int> =
        repository.observeSize()
}