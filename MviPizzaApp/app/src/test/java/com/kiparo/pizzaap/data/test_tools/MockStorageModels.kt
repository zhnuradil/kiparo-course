package com.kiparo.pizzaap.data.test_tools

import com.kiparo.pizzaapp.data.storage.model.MenuItemEntity
import com.kiparo.pizzaapp.data.storage.model.MenuSectionEntity

internal fun mockMenuItemEntity(id: String = "1") = MenuItemEntity(
    section = mockMenuSectionEntity(),
    id = id,
    title = "Kiparo Pizza",
    description = "Pizza description",
    image = "",
    price = "12"
)

internal fun mockMenuSectionEntity() = MenuSectionEntity(
    section = "all",
    image = ""
)
