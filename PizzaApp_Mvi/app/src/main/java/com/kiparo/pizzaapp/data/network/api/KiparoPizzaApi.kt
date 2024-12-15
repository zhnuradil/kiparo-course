package com.kiparo.pizzaapp.data.network.api

import com.kiparo.pizzaapp.data.network.model.MenuItemNetwork
import com.kiparo.pizzaapp.data.network.model.SectionItemNetwork

interface KiparoPizzaApi {
    fun getMenu(): List<MenuItemNetwork>
    fun getSections(): List<SectionItemNetwork>
}
