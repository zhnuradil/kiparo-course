/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.memory

import com.kiparo.pizzaapp.data.storage.api.CartStorage
import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

// This is a mocking solution for the learning purposes only
// to cache the items for the time of the app working
class CartMemoryStorage : CartStorage {
    private val cartItems: MutableSet<MenuItemEntity> = mutableSetOf()
    private val cartItemsState: MutableStateFlow<List<MenuItemEntity>> = MutableStateFlow(listOf())

    override fun add(menuItem: MenuItemEntity) {
        cartItems.add(menuItem)
        cartItemsState.update {
            cartItems.toList()
        }
    }

    override fun remove(id: String) {
        cartItems.removeAll { it.id == id }
        cartItemsState.update {
            cartItems.toList()
        }
    }

    override fun observe(): Flow<List<MenuItemEntity>> = cartItemsState

    override fun observeSize(): Flow<Int> = cartItemsState.map { it.size }
}