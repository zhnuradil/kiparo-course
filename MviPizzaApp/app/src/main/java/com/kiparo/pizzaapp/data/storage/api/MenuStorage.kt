/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.api

import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import com.kiparo.pizzaapp.data.storage.model.MenuSectionEntity

interface MenuStorage {

    fun add(items: List<MenuItemEntity>): Boolean

    fun getAll(): List<MenuItemEntity>

    fun getByFilter(filterBySection: String): List<MenuItemEntity>

    fun getById(id: String): MenuItemEntity

    fun addSections(sections: List<MenuSectionEntity>): Boolean

    fun getSections(): List<MenuSectionEntity>
}