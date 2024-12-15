/*
 * Copyright (c) 2023. Kiparo.ru
 */

@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.kiparo.pizzaapp.domain.usecases.cart

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.repository.CartRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithParams

class AddToCartUseCase(private val repository: CartRepository) : UseCaseWithParams<Unit, MenuItem> {
    override suspend fun execute(menuItem: MenuItem) =
        repository.add(item = menuItem)

}