/*
 * Copyright (c) 2023. Kiparo.ru
 */

@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.kiparo.pizzaapp.domain.usecases.menu

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.repository.MenuRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithParams

class GetSingleMenuItemUseCase(
    private val repository: MenuRepository,
) : UseCaseWithParams<MenuItem, String> {
    override suspend fun execute(menuItemId: String): MenuItem =
        repository.getById(id = menuItemId)
}