/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.api

import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import kotlinx.coroutines.flow.Flow

interface CartStorage {

    fun add(menuItem: MenuItemEntity)

    fun remove(id: String)

    fun observe(): Flow<List<MenuItemEntity>>

    fun observeSize(): Flow<Int>
}