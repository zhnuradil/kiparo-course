/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.domain.usecases.menu

import com.kiparo.pizzaapp.domain.models.MenuSection
import com.kiparo.pizzaapp.domain.repository.MenuRepository
import com.kiparo.pizzaapp.domain.usecases.UseCaseWithoutParams

class GetMenuSectionsUseCase(
    private val repository: MenuRepository,
) : UseCaseWithoutParams<List<MenuSection>> {
    override suspend fun execute(): List<MenuSection> {
        return repository.getSections()
    }
}