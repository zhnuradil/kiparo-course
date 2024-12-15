package com.kiparo.pizzaap.data.test_tools

import com.kiparo.pizzaapp.domain.models.MenuItem
import com.kiparo.pizzaapp.domain.models.MenuSection

internal fun mockMenuItem(id: String = "1") = MenuItem(
    section = mockMenuSection(),
    id = id,
    title = "Kiparo Pizza",
    description = "Pizza description",
    image = "",
    price = "12"
)

internal fun mockMenuSection() = MenuSection(
    section = "all",
    image = ""
)
