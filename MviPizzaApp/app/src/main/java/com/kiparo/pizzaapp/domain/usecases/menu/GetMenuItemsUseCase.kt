/*
 * Copyright (c) 2023. Kiparo.ru
 */

@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.kiparo.pizzaapp.domain.usecases.menu

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSectionFilter
import com.kiparo.pizzaapp.domain.repository.MenuRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithParams

class GetMenuItemsUseCase(
    private val repository: MenuRepository,
) : UseCaseWithParams<List<MenuItem>, String> {
    override suspend fun execute(filterBySection: String): List<MenuItem> {
        if (filterBySection == MenuSectionFilter.ALL.title)
            return repository.get(null)
        return repository.get(filterBySection = filterBySection)
    }
}