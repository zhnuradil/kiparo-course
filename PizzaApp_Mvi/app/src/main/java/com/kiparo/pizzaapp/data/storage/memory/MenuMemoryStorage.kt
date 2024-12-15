/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.pizzaapp.data.storage.memory

import com.kiparo.pizzaapp.data.storage.api.MenuStorage
import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import com.kiparo.pizzaapp.data.storage.model.MenuSectionEntity

// This is a mocking solution for the learning purposes only
// to cache the items for the time of the app working
class MenuMemoryStorage : MenuStorage {
    private val menuItems: MutableSet<MenuItemEntity> = mutableSetOf()
    private val sectionItems: MutableSet<MenuSectionEntity> = mutableSetOf()

    override fun add(items: List<MenuItemEntity>) = menuItems.addAll(items)

    override fun getAll() = menuItems.toList()

    override fun getByFilter(filterBySection: String) = menuItems.filter {
        it.section.section == filterBySection
    }

    override fun getById(id: String): MenuItemEntity =
        menuItems.first { it.id == id }

    override fun addSections(sections: List<MenuSectionEntity>) = sectionItems.addAll(sections)

    override fun getSections(): List<MenuSectionEntity> =
        sectionItems.toList()
}