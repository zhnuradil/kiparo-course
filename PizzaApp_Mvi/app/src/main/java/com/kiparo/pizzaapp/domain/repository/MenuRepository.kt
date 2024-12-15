/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.pizzaapp.domain.repository

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection

interface MenuRepository {
    suspend fun get(filterBySection: String?): List<MenuItem>
    suspend fun getById(id: String): MenuItem
    suspend fun getSections(): List<MenuSection>
}
