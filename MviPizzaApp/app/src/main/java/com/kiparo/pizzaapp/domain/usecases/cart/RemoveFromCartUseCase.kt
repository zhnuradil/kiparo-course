/*
 * Copyright (c) 2023. Kiparo.ru
 */

@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.kiparo.pizzaapp.domain.usecases.cart

import com.kiparo.pizzaapp.domain.repository.CartRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithParams

class RemoveFromCartUseCase(
    private val repository: CartRepository,
) : UseCaseWithParams<Unit, String> {
    override suspend fun execute(itemId: String) =
        repository.remove(id = itemId)
}