package com.kiparo.pizzaap.data.test_tools

import com.kiparo.pizzaapp.data.network.model.MenuItemNetwork
import com.kiparo.pizzaapp.data.network.model.SectionItemNetwork

internal fun mockMenuItemNetwork(id: String = "1") = MenuItemNetwork(
    section = "All",
    id = id,
    title = "Kiparo Pizza",
    description = "Pizza description",
    image = "",
    price = "12"
)

internal fun mockMenuSectionNetwork(section: String = "All") = SectionItemNetwork(
    section = section,
    image = ""
)
